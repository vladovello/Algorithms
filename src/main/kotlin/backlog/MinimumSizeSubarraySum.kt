class MinimumSizeSubarraySum {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var left = 0
        var sum = 0
        var minLen = Int.MAX_VALUE

        for (right in nums.indices) {
            sum += nums[right]

            while (sum >= target) {
                if (minLen > right - left + 1) {
                    minLen = right - left + 1
                }
                sum -= nums[left++]
            }
        }

        return if (minLen == Int.MAX_VALUE) 0 else minLen
    }
}