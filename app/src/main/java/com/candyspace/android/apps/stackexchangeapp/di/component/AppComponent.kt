package com.mujeeb.stackexchangeapp.di

import android.app.Application
import com.candyspace.android.apps.stackexchangeapp.StackExchangeApp
import com.candyspace.android.apps.stackexchangeapp.di.module.NetworkModule
import com.candyspace.android.apps.stackexchangeapp.di.scope.ApplicationScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule


@Component(
    modules = [AndroidInjectionModule::class, BuildMainActivityModule::class, NetworkModule::class]
)
@ApplicationScope
interface AppComponent {
    fun inject(stackExchangeApp: StackExchangeApp)

    @Component.Builder
    interface Builder {
        fun build(): AppComponent
        @BindsInstance
        fun application(application: Application): Builder
    }
}