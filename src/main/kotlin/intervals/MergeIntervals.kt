package intervals

fun main() {
    val intervals = arrayOf(
        intArrayOf(1, 3),
        intArrayOf(0, 6),
        intArrayOf(8, 10),
        intArrayOf(15, 18)
    )

    val mergedIntervals = MergeIntervals().merge(intervals)

    for (i in mergedIntervals.indices) {
        for (j in mergedIntervals[i].indices) {
            print("${mergedIntervals[i][j]} ")
        }

        println()
    }
}

class MergeIntervals {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        intervals.sortBy { it[0] }

        val mergedIntervals = Array(intervals.size) { IntArray(2) }

        val currentInterval = IntArray(2)
        currentInterval[0] = intervals[0][0]
        currentInterval[1] = intervals[0][1]

        var arrayIdx = 0

        for (interval in intervals) {
            if (currentInterval[1] >= interval[0]) {
                if (currentInterval[0] > interval[0]) currentInterval[0] = interval[0]
                if (currentInterval[1] < interval[1]) currentInterval[1] = interval[1]
                continue
            }

            mergedIntervals[arrayIdx][0] = currentInterval[0]
            mergedIntervals[arrayIdx][1] = currentInterval[1]
            ++arrayIdx

            currentInterval[0] = interval[0]
            currentInterval[1] = interval[1]
        }

        mergedIntervals[arrayIdx][0] = currentInterval[0]
        mergedIntervals[arrayIdx][1] = currentInterval[1]

        return mergedIntervals.sliceArray(0..arrayIdx)
    }
}