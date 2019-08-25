package com.sikic.pathfollowingalgorithm

import com.sikic.pathfollowingalgorithm.shared.AsciiMapType
import com.sikic.pathfollowingalgorithm.ui.main.MainActivityState
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert
import org.hamcrest.core.IsInstanceOf
import org.junit.Test

class MainActivityViewModelTestMap2 : BaseTestClass() {

    private val asciiMap2 =
                "**@*******\n" +
                "**|*C----+\n" +
                "**A*|****|\n" +
                "**+---B--+\n" +
                "****|******x\n" +
                "****|******|\n" +
                "****+---D--+"

    @Test
    fun shouldShowLoadedStateMap2_ProperFormat() {
        mainActivityViewModel.fetchAsciiMap(AsciiMapType.MAP2.value)
        MatcherAssert.assertThat(
            mainActivityViewModel.liveData.value,
            IsInstanceOf(MainActivityState.Loaded::class.java)
        )
        assertEquals(
            mainActivityViewModel.liveData.value,
            MainActivityState.Loaded(
                asciiMap = asciiMap2,
                path = "@|A+---B--+|+----C|-||+---D--+|x",
                letters = "ABCD"
            )
        )
    }
}