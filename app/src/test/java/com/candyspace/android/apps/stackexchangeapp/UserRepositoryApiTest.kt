package com.candyspace.android.apps.stackexchangeapp

import com.candyspace.android.apps.stackexchangeapp.api.StackOverflowApi
import com.candyspace.android.apps.stackexchangeapp.common.*
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito



class UserRepositoryApiTest : BaseTest() {


    private val users = TestUtils.generateUsers()
    @Mock
    lateinit var api: StackOverflowApi

    @Before
    override fun setup() {
        super.setup()
    }

    @Test
    fun testDataRetrievalFromApi() {
        Mockito.`when`(api.getUsers(anyString(), anyInt(), anyString(), anyString(), anyString()))
            .thenAnswer {
                return@thenAnswer Single.just(users)
            }

        val testObserver = api.getUsers("Jon", PAGE_SIZE, ORDER, SORT, SITE).test()

        testObserver.assertValueCount(1)

    }


}