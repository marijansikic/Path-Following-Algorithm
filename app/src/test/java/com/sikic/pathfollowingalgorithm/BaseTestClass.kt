package com.sikic.pathfollowingalgorithm

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule

abstract class BaseTestClass {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var mainActivityViewModel: MainActivityViewModel

    @Before
    fun reset() {
        mainActivityViewModel = MainActivityViewModel()
    }
}