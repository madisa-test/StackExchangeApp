package com.candyspace.android.apps.stackexchangeapp

import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.internal.schedulers.ExecutorScheduler
import io.reactivex.plugins.RxJavaPlugins
import org.junit.Before
import org.junit.BeforeClass
import org.mockito.MockitoAnnotations
import java.util.concurrent.Executor


open class BaseTest {
    companion object {
        @BeforeClass
        @JvmStatic
        fun setUpRxSchedulers() {
            val scheduler = object : Scheduler() {
                override fun createWorker(): Worker {
                    return ExecutorScheduler.ExecutorWorker(Executor { it.run() }, false)
                }
            }
            RxJavaPlugins.setInitIoSchedulerHandler { scheduler }
            RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler }
        }
    }

    @Before
    open fun setup() {
        MockitoAnnotations.initMocks(this)
    }


}