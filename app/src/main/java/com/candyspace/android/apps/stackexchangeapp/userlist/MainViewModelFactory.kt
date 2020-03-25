package com.candyspace.android.apps.stackexchangeapp.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.candyspace.android.apps.stackexchangeapp.common.NetworkUtils
import com.candyspace.android.apps.stackexchangeapp.repository.UsersRepositoryApi


class MainViewModelFactory(
    private val usersRepositoryApi: UsersRepositoryApi,
    private val utils: NetworkUtils
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(usersRepositoryApi, utils) as T
        }
        throw IllegalArgumentException("Please make sure the parameter is of type MainViewModel")
    }

}