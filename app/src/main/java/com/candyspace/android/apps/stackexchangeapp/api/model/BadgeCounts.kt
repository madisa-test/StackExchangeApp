package com.candyspace.android.apps.stackexchangeapp.api.model

import com.google.gson.annotations.SerializedName


data class BadgeCounts (

    @SerializedName("bronze") val bronze : Int,
    @SerializedName("silver") val silver : Int,
    @SerializedName("gold") val gold : Int
)