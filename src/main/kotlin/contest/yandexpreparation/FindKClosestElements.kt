package contest.yandexpreparation

import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

fun main() {
//    println(findClosestElements(intArrayOf(1, 2, 3, 4, 5), 4, 3).joinToString())
//    println(findClosestElements(intArrayOf(1, 2, 3, 4, 5), 4, -1).joinToString())
//    println(findClosestElements(intArrayOf(1, 4, 7, 9, 12), 3, 5).joinToString())
//    println(findClosestElements(intArrayOf(1, 4, 7, 9, 12), 3, 6).joinToString())
//    println(findClosestElements(intArrayOf(1, 12), 1, 1).joinToString())
//    println(findClosestElements(intArrayOf(1, 1, 1, 10, 10, 10), 1, 9).joinToString())
//    println(findClosestElements2(intArrayOf(0, 0, 1, 2, 3, 3, 4, 7, 7, 8), 3, 5).joinToString())
//    println(findClosestElements2(intArrayOf(0, 0, 1, 2, 3, 3, 4, 7, 7, 8), 3, 5).joinToString())
//    println(findClosestElements2(intArrayOf(1, 10, 15, 25, 35, 45, 50, 59), 1, 30).joinToString())
}

// По сути [mid, mid + k) - это окно, которое сдвигается в зависимости от того, насколько левее/правее оно от x.
// Выражение x - arr[mid] > arr[mid + k] - x выглядит именно так, без abs, потому что мы хотим рассмотреть
// положение x относительно окна.
fun findClosestElements2(arr: IntArray, k: Int, x: Int): IntArray {
    var left = 0
    var right = arr.size - k

    while (left != right) {
        val mid = (right + left) / 2

        if (x - arr[mid] > arr[mid + k] - x) left = mid + 1
        else right = mid
    }

    return arr.copyOfRange(left, left + k)
}

fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
    if (arr.size == 1) return arr.toList()

    var left = 0
    var right = arr.lastIndex

    while (left != right) {
        val mid = (left + right) / 2
        if (arr[mid] < x) left = mid + 1
        else right = mid
    }

    var leftBorder = arr.getNeighborWithMinDiff(left, x)
    var rightBorder = leftBorder

    while (rightBorder - leftBorder + 1 != k) {
        while (
            rightBorder - leftBorder + 1 != k &&
            leftBorder > 0 &&
            (rightBorder == arr.lastIndex || abs(arr[leftBorder - 1] - x) <= abs(arr[rightBorder + 1] - x))
        ) --leftBorder
        while (
            rightBorder - leftBorder + 1 != k &&
            rightBorder < arr.lastIndex &&
            (leftBorder == 0 || abs(arr[rightBorder + 1] - x) < abs(arr[leftBorder - 1] - x))
        ) ++rightBorder
    }

    return arr.slice(leftBorder..rightBorder)
}

// idx to value
private fun IntArray.getNeighborWithMinDiff(idx: Int, x: Int): Int {
    val left = max(idx - 1, 0)
    val right = min(idx + 1, this.lastIndex)

    var minIdx = 0
    var minDiff = Int.MAX_VALUE

    for (i in left..right) {
        val diff = abs(this[i] - x)
        if (diff < minDiff) {
            minDiff = diff
            minIdx = i
        }
    }

    return minIdx
}
