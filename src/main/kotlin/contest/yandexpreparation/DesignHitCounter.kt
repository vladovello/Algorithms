package contest.yandexpreparation

fun main() {
    val counter = ArrayHitCounter()

    counter.hit(1)

// hit at timestamp 2.
    counter.hit(2)

// hit at timestamp 3.
    counter.hit(3)

// get hits at timestamp 4, should return 3.
    println(counter.getHits(4))

// hit at timestamp 300.
    counter.hit(300)

// get hits at timestamp 300, should return 4.
    println(counter.getHits(300))

// get hits at timestamp 301, should return 3.
    println(counter.getHits(301))
}

private class ArrayHitCounter {
    private val hitRangeInSeconds = 300
    private var timestamps = IntArray(hitRangeInSeconds)
    private var hits = IntArray(hitRangeInSeconds)

    fun hit(timestamp: Int) {
        val idx = timestamp % hitRangeInSeconds
        if (timestamps[idx] != timestamp) {
            timestamps[idx] = timestamp
            hits[idx] = 1
        } else ++hits[idx]
    }

    fun getHits(timestamp: Int): Int {
        var totalHits = 0

        for (i in timestamps.indices) {
            if (timestamps[i] + hitRangeInSeconds > timestamp) totalHits += hits[i]
        }

        return totalHits
    }
}

private class QueueHitCounter {
    private var timestamps = mutableListOf<Int>()
    private var hits = mutableListOf<Int>()
    private val hitRangeInSeconds = 300

    fun hit(timestamp: Int) {
        if (timestamps.lastIndex != -1 && timestamps[timestamps.lastIndex] == timestamp) ++hits[timestamps.lastIndex]
        else {
            timestamps.add(timestamp)
            hits.add(1)
        }
//        removeIrrelevantTimestamps(timestamps.first())
    }

    fun getHits(timestamp: Int): Int {
        removeIrrelevantTimestamps(timestamp)
        return hits.sum()
    }

    private fun removeIrrelevantTimestamps(latestTimestamp: Int) {
        var left = 0
        var right = timestamps.lastIndex
        val lowerBound = latestTimestamp - hitRangeInSeconds + 1

        while (left != right) {
            val mid = (left + right) / 2

            if (timestamps[mid] >= lowerBound) right = mid
            // Number always should be equal or greater than latestTimestamp because of this
            else left = mid + 1
        }

        if (left != 0) {
            timestamps = timestamps.slice(left..timestamps.lastIndex).toMutableList()
            hits = hits.slice(left..hits.lastIndex).toMutableList()
        }
    }
}

private class HashMapHitCounter {
    private val hitRangeToHitsCount = mutableMapOf<Int, Int>()
    private val hitRangeInSeconds = 300

    fun hit(timestamp: Int) {
        hitRangeToHitsCount[timestamp] = (hitRangeToHitsCount[timestamp] ?: 0) + 1
    }

    fun getHits(timestamp: Int): Int {
        val entries = hitRangeToHitsCount.filter { it.key + hitRangeInSeconds > timestamp }.toList()
        return entries.sumOf { it.second }
    }
}
