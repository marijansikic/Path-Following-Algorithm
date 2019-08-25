package com.sikic.pathfollowingalgorithm.ui.main

sealed class MainActivityState {

   data class Loaded(
        val asciiMap: String,
        val path: String,
        val letters: String
    ) : MainActivityState()

   data class Invalid(
        val asciiMap: String,
        val path: String,
        val letters: String
    ) : MainActivityState()
}