fun main() {
    val nums = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
    println(RemoveDuplicates().removeDuplicates(nums))
    nums.forEach { println(it) }
}

private class RemoveDuplicates {
    fun removeDuplicates(nums: IntArray): Int {
        var placingPoint = 1
        var prev = nums[0]

        for (i in 1 until nums.size) {
            if (prev != nums[i]) {
                nums[placingPoint++] = nums[i]
                prev = nums[i]
            }
        }

        return placingPoint
    }
}
