package com.sikic.pathfollowingalgorithm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object MapRepository {

    private val mutableLiveData = MutableLiveData<String>()

    val liveData: LiveData<String> = mutableLiveData

    private const val asciiMap1 =
                "    @---A---+\n" +
                "            |\n" +
                "    x-B-+   C\n" +
                "        |   |\n" +
                "        +---+"

    private const val asciiMap2 =
                "  @       \n" +
                "  | C----+\n" +
                "  A |    |\n" +
                "  +---B--+\n" +
                "    |      x\n" +
                "    |      |\n" +
                "    +---D--+"

    private const val asciiMap3 =
                "  @---+    \n" +
                "      B    \n" +
                "K-----|--A \n" +
                "|     |  | \n" +
                "|  +--E  | \n" +
                "|  |     | \n" +
                "+--E--Ex C \n" +
                "   |     | \n" +
                "   +--F--+ "

    private const val asciiMap4 =
                "  @----+               \n" +
                "       |               \n" +
                "   E---H               \n" +
                "   |                   \n" +
                "   |   x               \n" +
                "   L   D    +---------+\n" +
                "   |   |    |         |\n" +
                "   +--------L         |\n" +
                "       |          +---O\n" +
                "     +-+          W    \n" +
                "     |            O    \n" +
                "     |            R    \n" +
                "     +------------L    \n"


    fun fetchMap(mapType: String) {

        when (mapType) {
            AsciiMapType.MAP1.value -> mutableLiveData.value = asciiMap1
            AsciiMapType.MAP2.value -> mutableLiveData.value = asciiMap2
            AsciiMapType.MAP3.value -> mutableLiveData.value = asciiMap3
            AsciiMapType.MAP4.value -> mutableLiveData.value = asciiMap4
            else -> readFileFromUri(mapType)
        }
    }

    private fun readFileFromUri(customMap: String) {
        mutableLiveData.value = customMap
    }
}