package com.candyspace.android.apps.stackexchangeapp.api

import com.candyspace.android.apps.stackexchangeapp.api.model.SoResponse
import com.candyspace.android.apps.stackexchangeapp.common.BASE_URL
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query


interface StackOverflowApi {
    @GET(BASE_URL)
    fun getUsers(
        @Query("inname") inmame: String,
        @Query("pagesize") pageSize: Int,
        @Query("order") order: String,
        @Query("sort") sort: String,
        @Query("site") site: String
    ): Single<SoResponse>
}