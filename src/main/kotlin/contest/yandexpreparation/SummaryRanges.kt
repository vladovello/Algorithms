package contest.yandexpreparation

fun main() {
    println(summaryRanges(intArrayOf(0, 1, 2, 4, 5, 7)))
    println(summaryRanges(intArrayOf()))
}

    fun summaryRanges(nums: IntArray): List<String> {
        if (nums.isEmpty()) return emptyList()

        val ranges = mutableListOf<String>()
        var currentRangeLeft = 0

        for (i in 1..nums.lastIndex) {
            if (nums[i] - nums[i - 1] == 1) continue

            ranges.add(createRange(nums[currentRangeLeft], nums[i - 1]))
            currentRangeLeft = i
        }

        ranges.add(createRange(nums[currentRangeLeft], nums.last()))

        return ranges
    }

private fun createRange(left: Int, right: Int) = if (left == right) "$left" else "$left->$right"
