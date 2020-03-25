package com.candyspace.android.apps.stackexchangeapp.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(

    @SerializedName("badge_counts") val badge_counts: BadgeCounts?,
    @SerializedName("account_id") val account_id: Int?,
    @SerializedName("is_employee") val is_employee: Boolean?,
    @SerializedName("last_modified_date") val last_modified_date: Int?,
    @SerializedName("last_access_date") val last_access_date: Int?,
    @SerializedName("reputation_change_year") val reputation_change_year: Int?,
    @SerializedName("reputation_change_quarter") val reputation_change_quarter: Int?,
    @SerializedName("reputation_change_month") val reputation_change_month: Int?,
    @SerializedName("reputation_change_week") val reputation_change_week: Int?,
    @SerializedName("reputation_change_day") val reputation_change_day: Int?,
    @SerializedName("reputation") val reputation: Int?,
    @SerializedName("creation_date") val creation_date: Int?,
    @SerializedName("user_type") val user_type: String?,
    @SerializedName("user_id") val user_id: Int?,
    @SerializedName("accept_rate") val accept_rate: Int?,
    @SerializedName("location") val location: String?,
    @SerializedName("website_url") val website_url: String?,
    @SerializedName("link") val link: String?,
    @SerializedName("profile_image") val profile_image: String?,
    @SerializedName("display_name") val display_name: String?
) : Parcelable
