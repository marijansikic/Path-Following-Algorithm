package com.sikic.pathfollowingalgorithm

enum class POI(val value: Char) {
    START(value = '@'),
    END(value = 'x'),
    HORIZONTAL_LINE('-'),
    VERTICAL_LINE('|'),
}