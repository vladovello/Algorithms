package twopointers

import java.util.Arrays

class `3SumReference` {

    fun threeSum(nums: IntArray): MutableList<MutableList<Int?>?> {
        Arrays.sort(nums)
        val ans = mutableListOf<MutableList<Int?>?>()
        val n = nums.size
        var i = 0
        while (i < n - 2 && nums[i] <= 0) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                ++i
                continue
            }
            var j = i + 1
            var k = n - 1
            while (j < k) {
                val x = nums[i] + nums[j] + nums[k]
                if (x < 0) {
                    ++j
                } else if (x > 0) {
                    --k
                } else {
                    ans.add(mutableListOf(nums[i], nums[j++], nums[k--]))
                    while (j < k && nums[j] == nums[j - 1]) {
                        ++j
                    }
                    while (j < k && nums[k] == nums[k + 1]) {
                        --k
                    }
                }
            }
            ++i
        }
        return ans
    }
}