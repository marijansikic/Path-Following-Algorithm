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
import com.sikic.pathfollowingalgorithm.ui.main.MainActivity
import org.hamcrest.Matchers.anything
import org.hamcrest.Matchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private val asciiMap1 =
                "****@---A---+\n" +
                "************|\n" +
                "****x-B-+***C\n" +
                "********|***|\n" +
                "********+---+"

    private val asciiMap2 =
                "**@*******\n" +
                "**|*C----+\n" +
                "**A*|****|\n" +
                "**+---B--+\n" +
                "****|******x\n" +
                "****|******|\n" +
                "****+---D--+"

    private val asciiMap3 =
                "**@---+****\n" +
                "******B****\n" +
                "K-----|--A*\n" +
                "|*****|**|*\n" +
                "|**+--E**|*\n" +
                "|**|*****|*\n" +
                "+--E--Ex*C*\n" +
                "***|*****|*\n" +
                "***+--F--+*"

    private val asciiMap4 =
        "**@----+***************\n" +
                "*******|***************\n" +
                "***E---H***************\n" +
                "***|*******************\n" +
                "***|***x***************\n" +
                "***L***D****+---------+\n" +
                "***|***|****|*********|\n" +
                "***+--------L*********|\n" +
                "*******|**********+---O\n" +
                "*****+-+**********W****\n" +
                "*****|************O****\n" +
                "*****|************R****\n" +
                "*****+------------L****\n"

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

        onView(withId(R.id.tvAsciiMap)).check(
            ViewAssertions.matches(
                withText(
                    containsString(
                        asciiMap1
                    )
                )
            )
        )

        onView(withId(R.id.tvPath)).check(ViewAssertions.matches(withText(containsString("@---A---+|C|+---+|+-B-x"))))

        onView(withId(R.id.tvLetters)).check(ViewAssertions.matches(withText(containsString("ACB"))))

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

        onView(withId(R.id.tvAsciiMap)).check(
            ViewAssertions.matches(
                withText(
                    containsString(
                        asciiMap2
                    )
                )
            )
        )
        onView(withId(R.id.tvPath)).check(ViewAssertions.matches(withText(containsString("@|A+---B--+|+----C|-||+---D--+|x"))))

        onView(withId(R.id.tvLetters)).check(ViewAssertions.matches(withText(containsString("ABCD"))))

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

        onView(withId(R.id.tvAsciiMap)).check(
            ViewAssertions.matches(
                withText(
                    containsString(
                        asciiMap3
                    )
                )
            )
        )
        onView(withId(R.id.tvPath)).check(ViewAssertions.matches(withText(containsString("@---+B||E--+|E|+--F--+|C|||A--|-----K|||+--E--Ex"))))

        onView(withId(R.id.tvLetters)).check(ViewAssertions.matches(withText(containsString("BEEFCAKE"))))

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

        onView(withId(R.id.tvAsciiMap)).check(
            ViewAssertions.matches(
                withText(
                    containsString(
                        asciiMap4
                    )
                )
            )
        )
        onView(withId(R.id.tvPath)).check(ViewAssertions.matches(withText(containsString("@----+|H---E||L|+--------L|+---------+||O---+WORL------------+||+-+|-|Dx"))))

        onView(withId(R.id.tvLetters)).check(ViewAssertions.matches(withText(containsString("HELLOWORLD"))))

        Thread.sleep(2000)
    }
}