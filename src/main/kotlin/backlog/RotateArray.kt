fun main() {
    val nums = intArrayOf(1, 2, 3, 4, 5, 6)
    RotateArray().rotate(nums, 3)
    nums.forEach { print("$it,") }
}

private class RotateArray {
    fun rotate(nums: IntArray, k: Int) {
        val step = k % nums.size

        if (step == 0) return

        nums.reverse(0, nums.size - 1)
        nums.reverse(0, step - 1)
        nums.reverse(step, nums.size - 1)
    }
}

private fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}

private fun IntArray.reverse(start: Int, end: Int) {
    for (i in start..(start + end) / 2) {
        this.swap(i, end - i + start)
    }
}
