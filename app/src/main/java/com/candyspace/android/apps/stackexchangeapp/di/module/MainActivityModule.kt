package com.candyspace.android.apps.stackexchangeapp.di.module


import androidx.lifecycle.ViewModelProviders
import com.candyspace.android.apps.stackexchangeapp.common.NetworkUtils
import com.candyspace.android.apps.stackexchangeapp.di.scope.MainActivityScope
import com.candyspace.android.apps.stackexchangeapp.repository.UsersRepositoryApi
import com.candyspace.android.apps.stackexchangeapp.userlist.MainActivity
import com.candyspace.android.apps.stackexchangeapp.userlist.MainViewModel
import com.candyspace.android.apps.stackexchangeapp.userlist.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {
    @Provides
    @MainActivityScope
    fun provideMainViewModelFactory(
        usersRepositoryApi: UsersRepositoryApi,
        networkUtils: NetworkUtils
    ) =
        MainViewModelFactory(usersRepositoryApi, networkUtils)

    @Provides
    @MainActivityScope
    fun provideMainViewModel(
        mainActivity: MainActivity,
        mainViewModelFactory: MainViewModelFactory
    ): MainViewModel {
        return ViewModelProviders.of(mainActivity, mainViewModelFactory)
            .get(MainViewModel::class.java)
    }
}