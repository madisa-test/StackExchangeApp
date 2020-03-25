package com.candyspace.android.apps.stackexchangeapp.userlist

import com.candyspace.android.apps.stackexchangeapp.api.model.User

interface UserSelectedInterface {
    fun onResultSelected(user: User)
}