package contest.yandexpreparation

import kotlin.math.max

fun main() {
    println(maxDistToClosest(intArrayOf(1, 0, 0, 0, 1, 0, 1)))
    println(maxDistToClosest(intArrayOf(1, 0, 0, 0)))
    println(maxDistToClosest(intArrayOf(0, 1)))
}

private fun maxDistToClosest(seats: IntArray): Int {
    var start = -1
    var maxDist = 0

    for (i in seats.indices) {
        if (seats[i] == 0) continue

        maxDist = if (start == -1) max(maxDist, i) else max(maxDist, (i - start) / 2)
        start = i
    }

    maxDist = max(maxDist, seats.lastIndex - start)

    return maxDist
}
