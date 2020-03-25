package com.candyspace.android.apps.stackexchangeapp

import android.app.Activity
import android.app.Application
import com.mujeeb.stackexchangeapp.di.AppComponent
import com.mujeeb.stackexchangeapp.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject


open class StackExchangeApp : Application(), HasActivityInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>


    private val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
            .application(this)
            .build()

    }

    override fun onCreate() {
        super.onCreate()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingAndroidInjector
}