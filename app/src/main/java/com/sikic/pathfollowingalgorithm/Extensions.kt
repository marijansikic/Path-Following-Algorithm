package com.sikic.pathfollowingalgorithm

val String.Companion.empty: String
    get() = ""

fun String.replaceBlanks() = this.replace(" ", "*")

fun Char.matchesPOI(): Boolean {
    return this.toString()
        .matches(
            "[A-W-+|x]"
                .toRegex()
        )
}

    fun Char.matchesLetters(): Boolean {
        return this.toString()
            .matches(
                "[A-Z]"
                    .toRegex()
            )
}