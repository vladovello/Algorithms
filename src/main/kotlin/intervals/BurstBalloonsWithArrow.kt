package intervals

fun main() {
    val points = arrayOf(
        intArrayOf(3, 9),
        intArrayOf(7, 12),
        intArrayOf(3, 8),
        intArrayOf(6, 8),
        intArrayOf(9, 10),
        intArrayOf(2, 9),
        intArrayOf(0, 9),
        intArrayOf(3, 9),
        intArrayOf(0, 6),
        intArrayOf(2, 8)
    )

    points.sortWith(compareBy { it[1] })

    for (i in points.indices) {
        for (j in points[i].indices) {
            print("${points[i][j]} ")
        }

        println()
    }

    println(BurstBalloonsWithArrow().findMinArrowShots(points))
}

class BurstBalloonsWithArrow {
    fun findMinArrowShots(points: Array<IntArray>): Int {
        points.sortWith(compareBy { it[1] })

        var rightBorder = points[0][1]
        var numberOfShots = 1

        for (i in 1..points.lastIndex) {
            if (points[i][0] <= rightBorder) continue

            ++numberOfShots
            rightBorder = points[i][1]
        }

        return numberOfShots
    }
}