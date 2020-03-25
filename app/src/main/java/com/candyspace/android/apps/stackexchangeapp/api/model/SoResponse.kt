package com.candyspace.android.apps.stackexchangeapp.api.model

import com.google.gson.annotations.SerializedName

data class SoResponse(

    @SerializedName("items") val items: List<User>,
    @SerializedName("has_more") val has_more: Boolean,
    @SerializedName("quota_max") val quota_max: Int,
    @SerializedName("quota_remaining") val quota_remaining: Int
)