class ProductOfArrayExceptSelf {
    fun productExceptSelf(nums: IntArray): IntArray {
        val answers = IntArray(nums.size) { 1 }
        var i = 1
        var j = nums.size - 2
        var prevSuffix = 1
        var prevPrefix = 1

        while (i < nums.size) {
            answers[i] *= nums[i - 1] * prevPrefix
            prevPrefix *= nums[i - 1]
            answers[j] *= nums[j + 1] * prevSuffix
            prevSuffix *= nums[j + 1]
            ++i
            --j
        }

        return answers
    }
}
