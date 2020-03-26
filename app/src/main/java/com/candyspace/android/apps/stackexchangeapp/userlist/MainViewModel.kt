package com.candyspace.android.apps.stackexchangeapp.userlist

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.candyspace.android.apps.stackexchangeapp.api.model.User
import com.candyspace.android.apps.stackexchangeapp.common.NetworkStatus
import com.candyspace.android.apps.stackexchangeapp.common.NetworkUtils
import com.candyspace.android.apps.stackexchangeapp.repository.UsersRepositoryApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(
    private val usersRepositoryApi: UsersRepositoryApi,
    private val utils: NetworkUtils
) : ViewModel() {

    private val usersObservable = MutableLiveData<List<User>>()
    private val errorObservable = MutableLiveData<NetworkStatus>()
    private val progressObservable = ObservableBoolean(false)

    private val compositeDisposable = CompositeDisposable()
    private var isLoading: Boolean = false

    fun getUsersObservable(): LiveData<List<User>> = usersObservable

    fun getErrorObservable() = errorObservable

    fun getProgressObservable() = progressObservable

    fun getData(name: String) {

        compositeDisposable.add(usersRepositoryApi.fetchUsers(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { processLoadingStateChange(true) }
            .doOnEvent { success, _ ->
                processLoadingStateChange(false)
                handleSubscription()
            }
            .subscribe({ handleSuccess(it) }, { handleError() })
        )


    }


    private fun handleSuccess(response: List<User>?) {

        usersObservable.value = response
    }

    private fun handleError() {
        errorObservable.value = NetworkStatus.FAIL
    }

    private fun handleSubscription() {
        if (!utils.isOnline()) {
            errorObservable.value = NetworkStatus.INTERNET_CONNECTION
        }
    }

    private fun processLoadingStateChange(isLoadingInProgress: Boolean) {
        isLoading = isLoadingInProgress

        progressObservable.set(isLoading)

    }

    override fun onCleared() {
        super.onCleared()
        if (isLoading) {
            processLoadingStateChange(false)
        }
        compositeDisposable.clear()
    }

}