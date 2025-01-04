package contest.yandexpreparation

import kotlin.math.max
import kotlin.math.min

fun main() {
    val first = arrayOf(intArrayOf(0, 2), intArrayOf(5, 10), intArrayOf(13, 23), intArrayOf(24, 25))
    val second = arrayOf(intArrayOf(1, 5), intArrayOf(8, 12), intArrayOf(15, 24), intArrayOf(25, 26))
    intervalIntersection(first, second).forEach { println(it.joinToString()) }
}

private fun intervalIntersection(firstList: Array<IntArray>, secondList: Array<IntArray>): Array<IntArray> {
    val intersections = mutableListOf<IntArray>()
    var firstPtr = 0
    var secondPtr = 0

    while (firstPtr < firstList.size && secondPtr < secondList.size) {
        // If segments not intersecting
        // If first is less than second
        if (firstList[firstPtr][1] < secondList[secondPtr][0]) {
            ++firstPtr
            continue
        }
        // If second is less than first
        else if (secondList[secondPtr][1] < firstList[firstPtr][0]) {
            ++secondPtr
            continue
        }

        val leftBorder = max(firstList[firstPtr][0], secondList[secondPtr][0])
        val rightBorder = min(firstList[firstPtr][1], secondList[secondPtr][1])

        intersections.add(intArrayOf(leftBorder, rightBorder))

        if (firstList[firstPtr][1] > secondList[secondPtr][1]) ++secondPtr
        else ++firstPtr
    }

    return intersections.toTypedArray()
}
