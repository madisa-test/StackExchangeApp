package com.candyspace.android.apps.stackexchangeapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.candyspace.android.apps.stackexchangeapp.api.model.User
import com.candyspace.android.apps.stackexchangeapp.common.NetworkStatus
import com.candyspace.android.apps.stackexchangeapp.common.NetworkUtils
import com.candyspace.android.apps.stackexchangeapp.repository.UsersRepositoryApi
import com.candyspace.android.apps.stackexchangeapp.userlist.MainViewModel
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.anyString
import org.mockito.Mockito.verify


class MainViewModelTest : BaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var usersRepositoryApi: UsersRepositoryApi

    @Mock
    lateinit var dataObserver: Observer<List<User>>

    @Mock
    lateinit var errorObserver: Observer<NetworkStatus>

    @Mock
    lateinit var utils: NetworkUtils

    private lateinit var mainViewModel: MainViewModel

    @Before
    override fun setup() {
        super.setup()
        mainViewModel = MainViewModel(usersRepositoryApi, utils)
    }

    @Test
    fun testDataSuccessfullyRetrievedWhenOnline() {

        val users = TestUtils.generateUsers()

        Mockito.`when`(usersRepositoryApi.fetchUsers(ArgumentMatchers.anyString()))
            .thenAnswer {
                return@thenAnswer Single.just(users)
            }

        Mockito.`when`(utils.isOnline())
            .thenAnswer {
                return@thenAnswer true
            }

        mainViewModel.getUsersObservable().observeForever(dataObserver)

        mainViewModel.getData("John")


        verify(dataObserver).onChanged(users)

    }


    @Test
    fun testOfflineAndEmptyDatabase() {
        Mockito.`when`(usersRepositoryApi.fetchUsers(anyString()))
            .thenAnswer {
                return@thenAnswer Single.just(true)
            }

        Mockito.`when`(utils.isOnline())
            .thenAnswer {
                return@thenAnswer false
            }


        mainViewModel.getErrorObservable().observeForever(errorObserver)
        mainViewModel.getData("John")


        verify(errorObserver).onChanged(
            NetworkStatus.FAIL
        )
    }


}