package com.sikic.pathfollowingalgorithm

import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsInstanceOf
import org.junit.Test

class MainActivityViewModelTestMap4 : BaseTestClass() {

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

private val invalidAsciiMap =
        "xT------S\n" +
        "    |\n" +
        "    " +
                "|\n" +
        "  @--T------E"

    @Test
    fun shouldShowLoadedStateMap4_ProperFormat() {
        mainActivityViewModel.fetchAsciiMap(AsciiMapType.MAP4.value)
        assertThat(
            mainActivityViewModel.liveData.value,
            IsInstanceOf(MainActivityState.Loaded::class.java)
        )
        assertEquals(
            mainActivityViewModel.liveData.value,
            MainActivityState.Loaded(
                asciiMap = asciiMap4,
                path = "@----+|H---E||L|+--------L|+---------+||O---+WORL------------+||+-+|-|Dx",
                letters = "HELLOWORLD"
            )
        )
    }

    @Test
    fun shouldShowLoadedStateCustom_ImproperFormat() {
        mainActivityViewModel.fetchAsciiMap(invalidAsciiMap)
        assertThat(
            mainActivityViewModel.liveData.value,
            IsInstanceOf(MainActivityState.Invalid::class.java)
        )
    }
}