package com.sikic.pathfollowingalgorithm

import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Test

class MainActivityViewModelTestMap3 : BaseTestClass() {

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

    @Test
    fun shouldShowLoadedStateMap3_ProperFormat() {
        mainActivityViewModel.fetchAsciiMap(AsciiMapType.MAP3.value)
        assertThat(
            mainActivityViewModel.liveData.value,
            IsInstanceOf(MainActivityState.Loaded::class.java)
        )
        assertEquals(
            mainActivityViewModel.liveData.value,
            MainActivityState.Loaded(
                asciiMap = asciiMap3,
                path = "@---+B||E--+|E|+--F--+|C|||A--|-----K|||+--E--Ex",
                letters = "BEEFCAKE"
            )
        )
    }
}