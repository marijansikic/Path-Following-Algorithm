package com.sikic.pathfollowingalgorithm

import com.sikic.pathfollowingalgorithm.shared.AsciiMapType
import com.sikic.pathfollowingalgorithm.ui.main.MainActivityState
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.Test

class MainActivityViewModelTestMap1 : BaseTestClass() {

    private val asciiMap1 =
                "****@---A---+\n" +
                "************|\n" +
                "****x-B-+***C\n" +
                "********|***|\n" +
                "********+---+"

    @Test
    fun shouldShowLoadedStateMap1_ProperFormat() {
        mainActivityViewModel.fetchAsciiMap(AsciiMapType.MAP1.value)
        MatcherAssert.assertThat(
            mainActivityViewModel.liveData.value,
            IsInstanceOf(MainActivityState.Loaded::class.java)
        )
        assertEquals(
            mainActivityViewModel.liveData.value,
            MainActivityState.Loaded(
                asciiMap = asciiMap1,
                path = "@---A---+|C|+---+|+-B-x",
                letters = "ACB"
            )
        )
    }
}