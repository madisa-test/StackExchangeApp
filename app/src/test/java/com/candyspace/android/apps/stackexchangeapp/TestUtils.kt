package com.candyspace.android.apps.stackexchangeapp

import com.candyspace.android.apps.stackexchangeapp.api.model.BadgeCounts
import com.candyspace.android.apps.stackexchangeapp.api.model.User



object TestUtils {
    fun generateUsers(): List<User> {

        val badgeCounts = BadgeCounts(
            3,
            1,
            3
        )
        val userOne = User(
            badgeCounts,
            1683,
            false,
            1559949014,
            1559999967,
            33289,
            14663,
            1624,
            1356,
            200,
            1110615,
            1222430705,
            "registered",
            22656,
            86,
            "Reading, United Kingdom",
            "http://csharpindepth.com",
            "https://stackoverflow.com/users/22656/jon-skeet",
            "https://www.gravatar.com/avatar/6d8ebb117e8d83d74ea95fbdd0f87e13?s=128&d=identicon&r=PG",
            "Jon Skeet"

        )

        return listOf(userOne)
    }
}

