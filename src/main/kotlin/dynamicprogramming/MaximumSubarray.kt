package dynamicprogramming

class MaximumSubarray {

    fun maxSubArray(nums: IntArray): Int {
        var maxSum = Int.MIN_VALUE
        var currentSum = 0

        for (i in nums.indices) {
            if (currentSum < 0 && nums[i] > nums.getOrElse(i - 1) { Int.MAX_VALUE }) {
                currentSum = nums[i]
            } else {
                currentSum += nums[i]
            }

            if (currentSum > maxSum) {
                maxSum = currentSum
            }
        }

        return maxSum
    }

    fun maxSubArrayReference(nums: IntArray): Int {
        var maxSum = nums[0]
        var currentSum = nums[0]

        for (i in 1..nums.lastIndex) {
            currentSum = maxOf(nums[i], currentSum + nums[i])
            maxSum = maxOf(maxSum, currentSum)
        }

        return maxSum
    }
}
