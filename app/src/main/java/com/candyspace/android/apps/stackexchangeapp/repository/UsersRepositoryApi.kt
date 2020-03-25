package com.worldremit.sousers.repository

import com.candyspace.android.apps.stackexchangeapp.api.StackOverflowApi
import com.candyspace.android.apps.stackexchangeapp.api.model.User
import com.candyspace.android.apps.stackexchangeapp.common.ORDER
import com.candyspace.android.apps.stackexchangeapp.common.PAGE_SIZE
import com.candyspace.android.apps.stackexchangeapp.common.SITE
import com.candyspace.android.apps.stackexchangeapp.common.SORT
import io.reactivex.Single
import javax.inject.Inject

class UsersRepositoryApi @Inject constructor(private val api: StackOverflowApi)  {

    fun fetchUsers(inname : String): Single<List<User>> {
        return api.getUsers(inname, PAGE_SIZE, ORDER, SORT, SITE).map { response -> response.items }

    }
}