package contest.yandexpreparation

fun main() {
    println(intArrayOf(0, 1, 0, 3, 5).also { moveZeroes(it) }.joinToString())
    println(intArrayOf(1, 0, 2, 3, 0, 0, 6, 0, 7).also { moveZeroes(it) }.joinToString())
}

private fun moveZeroes(nums: IntArray) {
    var firstZeroIdx = -1

    for (i in nums.indices) {
        if (nums[i] == 0 && firstZeroIdx == -1) firstZeroIdx = i
        else if (nums[i] != 0 && firstZeroIdx != -1) nums.swap(i, firstZeroIdx++)
    }
}

private fun moveZeroes2(nums: IntArray) {
    var nonZeroesPos = 0
    for (i in nums.indices) if (nums[i] != 0) nums[nonZeroesPos++] = nums[i]
    for (i in nonZeroesPos..nums.lastIndex) nums[i] = 0
}

private fun IntArray.swap(i: Int, j: Int) {
    this[i] = this[i] xor this[j]
    this[j] = this[i] xor this[j]
    this[i] = this[i] xor this[j]
}
