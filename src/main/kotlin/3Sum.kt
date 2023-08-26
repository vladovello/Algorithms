class `3Sum` {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()

        val triplets = mutableListOf<List<Int>>()

        for (i in 0..nums.lastIndex - 2) {
            if (nums[i] > 0) break
            if (i > 0 && nums[i] == nums[i - 1]) continue

            val first = nums[i]

            var left = i + 1
            var right = nums.lastIndex

            while (left < right) {
                val sum = first + nums[left] + nums[right]

                when {
                    sum == 0 -> {
                        triplets.add(listOf(first, nums[left], nums[right]))

                        do ++left while (left < right && nums[left] == nums[left - 1])
                        do --right while (left < right && nums[right] == nums[right + 1])
                    }

                    sum < 0 -> ++left
                    else -> --right
                }
            }
        }

        return triplets
    }
}