package com.candyspace.android.apps.stackexchangeapp.di.module


import com.candyspace.android.apps.stackexchangeapp.di.scope.MainActivityScope
import com.candyspace.android.apps.stackexchangeapp.userlist.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildMainActivityModule {
    @ContributesAndroidInjector(modules = [MainActivityModule::class, AdapterModule::class])
    @MainActivityScope
    abstract fun mainActivity(): MainActivity


}