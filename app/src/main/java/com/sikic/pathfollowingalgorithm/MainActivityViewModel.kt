package com.sikic.pathfollowingalgorithm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.sikic.pathfollowingalgorithm.Traversal.*

class MainActivityViewModel : ViewModel(), Observer<String> {

    private val mutableLiveData = MutableLiveData<MainActivityState>()
    val liveData: LiveData<MainActivityState> = mutableLiveData

    private var arr = mutableListOf<Char>()
    private var lastTraversal = START
    private var path: String = ""
    private var letters: String = ""
    private var visitedFields = mutableListOf<Pair<Int, Int>>()

    override fun onChanged(newValue: String) {
        calculateAlgorithm(newValue)
    }

    init {
        reset()
        MapRepository.liveData.observeForever(this)
    }

    private fun reset() {
        arr = mutableListOf<Char>()
        lastTraversal = START
        path = ""
        letters = ""
        visitedFields = mutableListOf<Pair<Int, Int>>()
    }

    fun fetchAsciiMap(mapType: String) {
        reset()
        MapRepository.fetchMap(mapType)
    }

    private fun calculateAlgorithm(asciiMap: String) {
        val matrix = convertMapToMatrix(asciiMap)
        val firstElement = findFirstElement(matrix)

        calculatePossibleRoutes(matrix, firstElement)
        print("$path $letters")

        if (path.first() == POI.START.value && path.last() == POI.END.value) {
            mutableLiveData.value =
                MainActivityState.Loaded(
                    asciiMap = asciiMap.replace(" ", "*"),
                    path = path,
                    letters = letters
                )
        } else {
            mutableLiveData.value =
                MainActivityState.Invalid(
                    asciiMap = asciiMap.replace(" ", "*"),
                    path = path,
                    letters = letters
                )
        }
    }

    private fun calculatePossibleRoutes(matrix: List<CharArray>, elementPosition: Pair<Int, Int>) {

        val currentElement = matrix[elementPosition.first][elementPosition.second]
        if (currentElement == POI.END.value) {
            return
        }

        if (elementPosition.first - 1 >= 0
            && elementPosition.second < matrix[elementPosition.first - 1].size
            && matrix[elementPosition.first - 1][elementPosition.second].toString().matches("[A-W-+|x]".toRegex())
            && lastTraversal != DOWN
            && currentElement != POI.HORIZONTAL_LINE.value
        ) {
            traverseIntoDirection(matrix, elementPosition, UP)

        } else if (elementPosition.first + 1 < matrix.size
            && elementPosition.second < matrix[elementPosition.first + 1].size
            && matrix[elementPosition.first + 1][elementPosition.second].toString().matches(
                "[A-W-+|x]".toRegex()
            ) && lastTraversal != UP
            && currentElement != POI.HORIZONTAL_LINE.value
        ) {
            traverseIntoDirection(matrix, elementPosition, DOWN)

        } else if (elementPosition.second - 1 < matrix[elementPosition.first].size
            && elementPosition.second > 0
            && matrix[elementPosition.first][elementPosition.second - 1].toString().matches(
                "[A-W-+|x]".toRegex()
            ) && lastTraversal != RIGHT
            && currentElement != POI.VERTICAL_LINE.value
        ) {
            traverseIntoDirection(matrix, elementPosition, LEFT)

        } else if (elementPosition.second + 1 < matrix[elementPosition.first].size
            && matrix[elementPosition.first][elementPosition.second + 1].toString().matches(
                "[A-W-+|x]".toRegex()
            ) && lastTraversal != LEFT
            && currentElement != POI.VERTICAL_LINE.value
        ) {
            traverseIntoDirection(matrix, elementPosition, RIGHT)
        }
    }

    private fun traverseIntoDirection(
        matrix: List<CharArray>,
        elementPosition: Pair<Int, Int>,
        traversalDirection: Traversal
    ) {

        val element: Char
        when (traversalDirection) {
            UP -> {
                if (visitedFields.contains(
                        Pair(
                            elementPosition.first - 1,
                            elementPosition.second
                        )
                    )
                ) {
                    print("Visited")

                    path += matrix[elementPosition.first - 1][elementPosition.second]
                    element = matrix[elementPosition.first - 2][elementPosition.second]
                    visitedFields.add(Pair(elementPosition.first - 2, elementPosition.second))
                    arr.add(matrix[elementPosition.first - 2][elementPosition.second])

                    mapElementToValue(element)

                    println(arr)
                    lastTraversal = UP
                    calculatePossibleRoutes(
                        matrix,
                        elementPosition.copy(first = elementPosition.first - 2)
                    )
                } else {
                    print("NOT Visited")
                    arr.add(matrix[elementPosition.first - 1][elementPosition.second])
                    println(arr)
                    element = matrix[elementPosition.first - 1][elementPosition.second]
                    visitedFields.add(Pair(elementPosition.first - 1, elementPosition.second))
                    mapElementToValue(element)

                    lastTraversal = UP

                    calculatePossibleRoutes(
                        matrix,
                        elementPosition.copy(first = elementPosition.first - 1)
                    )
                }
            }

            DOWN -> {
                if (visitedFields.contains(
                        Pair(
                            elementPosition.first + 1,
                            elementPosition.second
                        )
                    )
                ) {
                    print("Visited")

                    path += matrix[elementPosition.first + 1][elementPosition.second]
                    element = matrix[elementPosition.first + 2][elementPosition.second]
                    visitedFields.add(Pair(elementPosition.first + 2, elementPosition.second))
                    arr.add(matrix[elementPosition.first + 2][elementPosition.second])

                    mapElementToValue(element)

                    println(arr)
                    lastTraversal = DOWN
                    calculatePossibleRoutes(
                        matrix,
                        elementPosition.copy(first = elementPosition.first + 2)
                    )
                } else {
                    print("NOT Visited")
                    element = matrix[elementPosition.first + 1][elementPosition.second]
                    visitedFields.add(Pair(elementPosition.first + 1, elementPosition.second))
                    arr.add(matrix[elementPosition.first + 1][elementPosition.second])

                    mapElementToValue(element)

                    println(arr)
                    lastTraversal = DOWN
                    calculatePossibleRoutes(
                        matrix,
                        elementPosition.copy(first = elementPosition.first + 1)
                    )
                }
            }
            LEFT -> {
                if (visitedFields.contains(
                        Pair(
                            elementPosition.first,
                            elementPosition.second - 1
                        )
                    )
                ) {
                    print("Visited")

                    path += matrix[elementPosition.first][elementPosition.second - 1]
                    element = matrix[elementPosition.first][elementPosition.second - 2]
                    visitedFields.add(Pair(elementPosition.first, elementPosition.second - 2))
                    arr.add(matrix[elementPosition.first][elementPosition.second - 2])

                    mapElementToValue(element)

                    println(arr)
                    lastTraversal = LEFT
                    calculatePossibleRoutes(
                        matrix,
                        elementPosition.copy(second = elementPosition.second - 2)
                    )
                } else {
                    print("NOT Visited")
                    element = matrix[elementPosition.first][elementPosition.second - 1]
                    visitedFields.add(Pair(elementPosition.first, elementPosition.second - 1))
                    mapElementToValue(element)

                    arr.add(matrix[elementPosition.first][elementPosition.second - 1])
                    println(arr)
                    lastTraversal = LEFT

                    calculatePossibleRoutes(
                        matrix,
                        elementPosition.copy(second = elementPosition.second - 1)
                    )
                }
            }
            RIGHT -> {
                if (visitedFields.contains(
                        Pair(
                            elementPosition.first,
                            elementPosition.second + 1
                        )
                    )
                ) {
                    print("Visited")

                    path += matrix[elementPosition.first][elementPosition.second + 1]
                    element = matrix[elementPosition.first][elementPosition.second + 2]
                    visitedFields.add(Pair(elementPosition.first, elementPosition.second + 2))
                    arr.add(matrix[elementPosition.first][elementPosition.second + 2])

                    mapElementToValue(element)

                    println(arr)
                    lastTraversal = RIGHT
                    calculatePossibleRoutes(
                        matrix,
                        elementPosition.copy(second = elementPosition.second + 2)
                    )
                } else {
                    print("NOT Visited")
                    element = matrix[elementPosition.first][elementPosition.second + 1]
                    visitedFields.add(Pair(elementPosition.first, elementPosition.second + 1))
                    mapElementToValue(element)

                    arr.add(matrix[elementPosition.first][elementPosition.second + 1])
                    lastTraversal = RIGHT

                    println(arr)
                    calculatePossibleRoutes(
                        matrix,
                        elementPosition.copy(second = elementPosition.second + 1)
                    )
                }
            }
            START -> {
                // no implementation since it will never happen
            }
        }
    }

    private fun mapElementToValue(element: Char) {
        /* StringBuffer/StringBuilder would be proper way to concat Strings, but since this is not something resource heavy,
           we sacrifice minimum performance for better readability
        * */
        path += element
        if (element.toString().matches("[A-Z]".toRegex())) {
            letters += element
        }
    }

    private fun findFirstElement(
        matrix: List<CharArray>
    ): Pair<Int, Int> {

        var firstElementColumnIndex = 0
        var firstElementRowIndex1 = 0
        matrix.forEachIndexed { columnIndex, charArray ->
            if (charArray.contains(POI.START.value)) {
                firstElementColumnIndex = columnIndex
                firstElementRowIndex1 = charArray.indexOf(POI.START.value)
                path += POI.START.value
                return firstElementColumnIndex to firstElementRowIndex1

            }
        }
        return firstElementColumnIndex to firstElementRowIndex1
    }

    private fun convertMapToMatrix(asciiMap: String): List<CharArray> = asciiMap.split("\n").map {
        it.toCharArray()
    }
}

