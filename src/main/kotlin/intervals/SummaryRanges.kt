package intervals

fun main() {
    val nums = intArrayOf(1, 2, 3, 4, 6, 7, 9, 12)
    println(SummaryRanges().summaryRanges(nums))
}

class SummaryRanges {
    fun summaryRanges(nums: IntArray): List<String> {
        if (nums.isEmpty()) return listOf()

        val ranges = mutableListOf<String>()
        var rangeStart = nums[0]

        for (i in 0 until nums.lastIndex) {
            if (nums[i + 1] - nums[i] != 1) {
                ranges.add(formatRange(rangeStart, nums[i]))
                rangeStart = nums[i + 1]
            }
        }

        ranges.add(formatRange(rangeStart, nums.last()))

        return ranges
    }

    private fun formatRange(start: Int, end: Int): String {
        return if (start == end) "$start" else "$start->$end"
    }
}