package com.candyspace.android.apps.stackexchangeapp.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BadgeCounts (

    @SerializedName("bronze") val bronze : Int,
    @SerializedName("silver") val silver : Int,
    @SerializedName("gold") val gold : Int
): Parcelable