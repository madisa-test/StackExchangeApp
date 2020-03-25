package com.candyspace.android.apps.stackexchangeapp.userlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.worldremit.sousers.repository.UsersRepositoryApi


class MainViewModelFactory(
    private val usersRepositoryApi: UsersRepositoryApi
) : ViewModelProvider.Factory {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(usersRepositoryApi) as T
        }
        throw IllegalArgumentException("Please make sure the parameter is of type MainViewModel")
    }

}