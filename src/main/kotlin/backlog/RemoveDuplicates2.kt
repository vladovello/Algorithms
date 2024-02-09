fun main() {
    val nums = intArrayOf(0, 0, 1, 1, 1, 1, 2, 3, 3)
    println(RemoveDuplicates2().removeDuplicates(nums))
    nums.forEach { println(it) }
}

private class RemoveDuplicates2 {
    fun removeDuplicates(nums: IntArray): Int {
        var placingPoint = 1
        var prev = nums[0]
        var currOccurCount = 1

        for (i in 1 until nums.size) {
            if (prev == nums[i]) {
                ++currOccurCount
            } else {
                currOccurCount = 1
                prev = nums[i]
            }

            if (currOccurCount <= 2) {
                nums[placingPoint++] = nums[i]
            }
        }

        return placingPoint
    }
}