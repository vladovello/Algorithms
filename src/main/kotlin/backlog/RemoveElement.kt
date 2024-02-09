fun main() {
    val nums = intArrayOf(0, 1, 2, 2, 3, 0, 4, 2)
    val `val` = 2
    println(RemoveElement().removeElement(nums, `val`))
}

private class RemoveElement {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var placingPoint = 0

        for (i in nums.indices) {
            if (nums[i] == `val`) {
                continue
            }

            nums.swap(placingPoint++, i)
        }

        return placingPoint
    }
}

private fun IntArray.swap(i: Int, j: Int) {
    val temp = this[i]
    this[i] = this[j]
    this[j] = temp
}