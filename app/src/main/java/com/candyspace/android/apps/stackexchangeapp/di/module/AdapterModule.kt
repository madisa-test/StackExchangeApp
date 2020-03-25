package com.candyspace.android.apps.stackexchangeapp.di.module

import com.candyspace.android.apps.stackexchangeapp.di.scope.MainActivityScope
import com.candyspace.android.apps.stackexchangeapp.userlist.MainActivity
import com.candyspace.android.apps.stackexchangeapp.userlist.UserSelectedInterface
import com.candyspace.android.apps.stackexchangeapp.userlist.UsersAdapter
import dagger.Module
import dagger.Provides

@Module
class AdapterModule {
    @Provides
    @MainActivityScope
    fun provideUsersAdapter(userSelectedInterface: UserSelectedInterface): UsersAdapter {
        return UsersAdapter(userSelectedInterface)
    }

    @Provides
    @MainActivityScope
    fun provideUserSelectedInterface(mainActivity: MainActivity): UserSelectedInterface {
        return mainActivity
    }
}