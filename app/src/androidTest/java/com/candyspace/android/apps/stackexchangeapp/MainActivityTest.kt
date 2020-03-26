package com.candyspace.android.apps.stackexchangeapp

import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.candyspace.android.apps.stackexchangeapp.userlist.MainActivity
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.idling.CountingIdlingResource
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.After
import org.junit.Before

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    val mainActivityTestRule = ActivityTestRule (MainActivity::class.java, true, false)
    @Before
    fun setup() {
        IdlingRegistry.getInstance().register(CountingIdlingResource("GLOBAL"))
        //launch activity
        mainActivityTestRule.launchActivity(Intent())
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(CountingIdlingResource("GLOBAL"))
    }

    @Test
    fun checkStaticView() {
        //verify default empty recyclerview
        onView(withId(R.id.bt_search)).check(matches(isDisplayed()))
        // verify default empty text message
        onView(withId(R.id.et_name)).check(matches(isDisplayed()))


    }

    @Test
    fun shouldShowSnackBarUsernameEmpty() {
        // empty text input
        onView(withId(R.id.et_name))
            .perform(typeText(""), closeSoftKeyboard())

        // perform button click
        onView(withId(R.id.bt_search)).perform(click())

        // verify the snackbaer text
        onView(withText("Please enter a name")).check(matches(isDisplayed()))


    }

    @Test
    fun checkRecyclerView() {

        // Get total item of RecyclerView
        val itemCount = 20

        //input text
        onView(withId(R.id.et_name))
            .perform(typeText("Jon"), closeSoftKeyboard())

        // perform button click
        onView(withId(R.id.bt_search)).perform(click())

        //wait for loading & progress bar animation
        Thread.sleep(15000)

        //verify recycler view is displayed with 20 items
        onView(withId(R.id.rv_user_list)).check(matches(CustomMatchers.withItemCount(itemCount)))

        // Scroll to end of page with position
        onView(withId(R.id.rv_user_list))
            .perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(itemCount - 1))


        // perform click on item at 0th position
        onView(withId(R.id.rv_user_list)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )


    }



}

