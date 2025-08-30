package dynamicprogramming

import kotlin.math.max

object HouseRobberSolutions {

    class Solution1 {

        fun rob(nums: IntArray): Int {
            if (nums.size <= 2) return nums.max()

            val prefixSum = IntArray(nums.size)
            var maxLoot = 0
            prefixSum[0] = nums[0]
            prefixSum[1] = max(nums[0], nums[1])

            for (i in 2..nums.lastIndex) {
                maxLoot = max(prefixSum[i - 2] + nums[i], prefixSum[i - 1])
                prefixSum[i] = maxLoot
            }

            return maxLoot
        }
    }
}
