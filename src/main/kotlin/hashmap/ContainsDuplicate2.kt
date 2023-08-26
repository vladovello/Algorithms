package hashmap

import kotlin.math.min

fun main() {
    val nums = intArrayOf(99, 99)
    val k = 2

    println(ContainsDuplicate2().containsNearbyDuplicate2(nums, k))
}

class ContainsDuplicate2 {
    fun containsNearbyDuplicate2(nums: IntArray, k: Int): Boolean {
        val repeats = hashMapOf<Int, Int>()

        for (i in 0 until min(k, nums.size)) {
            repeats[nums[i]] = i
        }

        for (i in nums.indices) {
            if (i + k < nums.size) repeats[nums[i + k]] = i + k

            if (repeats.containsKey(nums[i])) {
                val numIdx = repeats[nums[i]]!!

                if (numIdx - i > 0) return true
            }
        }

        return false
    }

    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val repeats = hashMapOf<Int, Int>()

        for ((i, n) in nums.withIndex()) {
            if (repeats.containsKey(n)) {
                val prevNumIdx = repeats[n]!!

                if (i - prevNumIdx <= k) return true
            }

            repeats[n] = i
        }

        return false
    }
}