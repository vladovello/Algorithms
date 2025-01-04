package yandexpreparation

import kotlin.math.max
import kotlin.math.min

fun main() {
    val points = arrayOf(intArrayOf(1, 1), intArrayOf(1, -1))
    println(isSymmetricalToOrdinateAxis(points))
}

private fun isSymmetricalToOrdinateAxis(points: Array<IntArray>): Boolean {
    val reflectedPoints = mutableSetOf<Pair<Int, Int>>()
    var minX = Int.MAX_VALUE
    var maxX = Int.MIN_VALUE

    for (point in points) {
        minX = min(minX, point[0])
        maxX = max(maxX, point[0])
        reflectedPoints.add(point.toPoint())
    }

    val sum = minX + maxX

    for (point in reflectedPoints) {
        if (!reflectedPoints.contains(sum - point.first to point.second)) return false
    }

    return true
}

// sum = min + max
// m = sum / 2
// x1 - given; x1r - ?
// m + (m - x1) = 2m + x1 = sum - x1
// x1r - given; x1 - ?
// m - (x1r - m) = 2m - x1r = sum - x1r

private fun IntArray.toPoint() = this[0] to this[1]
