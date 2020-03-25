package com.candyspace.android.apps.stackexchangeapp.userlist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.databinding.ObservableBoolean
import com.candyspace.android.apps.stackexchangeapp.api.model.User
import com.worldremit.sousers.repository.UsersRepositoryApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val usersRepositoryApi: UsersRepositoryApi) : ViewModel() {

    private val usersObservable = MutableLiveData<List<User>>()
    private val errorObservable = MutableLiveData<String>()
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
                handleSubscription(success?.equals(null) ?: true)
            }
            .subscribe({ handleSuccess(it) }, { handleError(it.message) })
        )


    }


    private fun handleSuccess(response: List<User>?) {

        usersObservable.value = response
    }

    private fun handleError(errorMessage: String?) {
        errorObservable.value = errorMessage
    }

    private fun handleSubscription(isEmpty: Boolean) {
        if (isEmpty) {
            errorObservable.value = " Data Not Available"
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