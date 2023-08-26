package intervals

import kotlin.math.max
import kotlin.math.min

class InsertInterval {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        var insertIdx = 0
        var insertLen = 0

        for (i in intervals.indices) {
            when (compareIntervals(newInterval, intervals[i])) {
                1 -> insertIdx = i + 1

                0 -> {
                    newInterval[0] = min(newInterval[0], intervals[i][0])
                    newInterval[1] = max(newInterval[1], intervals[i][1])
                    ++insertLen
                }

                -1 -> return intervals.copyWithInsert(insertIdx, newInterval, insertLen)
            }

            if (i == intervals.lastIndex) return intervals.copyWithInsert(insertIdx, newInterval, insertLen)
        }

        return arrayOf(newInterval)
    }

    /**
     * @return `1` if `interval1` is greater than `interval2`, `0` if intervals are intersecting and `-1` if `interval2` is less than `interval1`
     */
    private fun compareIntervals(interval1: IntArray, interval2: IntArray): Int {
        return if (interval1[0] > interval2[1]) 1 else if (interval1[1] < interval2[0]) -1 else 0
    }
}

inline fun <reified T> Array<T>.copyWithInsert(insertIndex: Int, element: T, insertLength: Int = 0): Array<T> {
    return Array(this.size + 1 - insertLength) {
        when (it) {
            in 0 until insertIndex -> this[it]
            insertIndex -> element
            else -> this[it - 1 + insertLength]
        }
    }
}