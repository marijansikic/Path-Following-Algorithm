package com.sikic.pathfollowingalgorithm

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.RootMatchers.isDialog
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.anything
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun performButtonClick_ShouldShowDialogAndShowAscii1() {
        onView(withId(R.id.btnSelectSource))
            .perform(click())

        onView(withText(R.string.dialog_title))
            .inRoot(isDialog())
            .check(ViewAssertions.matches(isDisplayed()))

        onData(anything()).atPosition(0).perform(click())

        Thread.sleep(2000)
    }


    @Test
    fun performButtonClick_ShouldShowDialogAndShowAscii2() {
        onView(withId(R.id.btnSelectSource))
            .perform(click())

        onView(withText(R.string.dialog_title))
            .inRoot(isDialog())
            .check(ViewAssertions.matches(isDisplayed()))

        onData(anything()).atPosition(1).perform(click())

        Thread.sleep(2000)
    }


    @Test
    fun performButtonClick_ShouldShowDialogAndShowAscii3() {
        onView(withId(R.id.btnSelectSource))
            .perform(click())

        onView(withText(R.string.dialog_title))
            .inRoot(isDialog())
            .check(ViewAssertions.matches(isDisplayed()))

        onData(anything()).atPosition(2).perform(click())

        Thread.sleep(2000)
    }

    @Test
    fun performButtonClick_ShouldShowDialogAndShowAscii4() {
        onView(withId(R.id.btnSelectSource))
            .perform(click())

        onView(withText(R.string.dialog_title))
            .inRoot(isDialog())
            .check(ViewAssertions.matches(isDisplayed()))

        onData(anything()).atPosition(3).perform(click())

        Thread.sleep(2000)
    }
}