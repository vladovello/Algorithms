package dynamicprogramming

import kotlin.math.max

interface LongestIncreasingSubsequence {

    fun lengthOfLIS(nums: IntArray): Int
}

object LongestIncreasingSubsequenceSolutions {
    /**
     * Almost O(n^2) by time and O(n) by space
     */
    class BruteForceSolution : LongestIncreasingSubsequence {

        override fun lengthOfLIS(nums: IntArray): Int {
            val lis = IntArray(nums.size)
            var allLongest = 0

            for (i in lis.indices) {
                val current = nums[i]
                var currentLongest = 0

                for (j in 0..<i) {
                    if (current > nums[j]) {
                        currentLongest = max(lis[j], currentLongest)
                    }
                }

                lis[i] = currentLongest + 1
                allLongest = max(lis[i], allLongest)
            }

            return allLongest
        }
    }

    /**
     * O(n*log(n)) by time and O(n) by space
     */
    class BinarySearchSolution : LongestIncreasingSubsequence {

        override fun lengthOfLIS(nums: IntArray): Int {
            val lis = mutableListOf<Int>()

            for (n in nums) {
                if ((lis.lastOrNull() ?: Int.MIN_VALUE) < n) {
                    lis.add(n)
                    continue
                }

                lis[myGetReplaceIdx(lis, n)] = n
            }

            return lis.size
        }

        private fun myGetReplaceIdx(lis: List<Int>, n: Int): Int {
            var left = 0
            var right = lis.lastIndex

            while (left <= right) {
                val mid = (left + right).ushr(1)
                val midValue = lis[mid]

                if (midValue < n) left = mid + 1
                else if (midValue > n) right = mid - 1
                else return mid
            }

            return left
        }

        private fun builtInGetReplaceIdx(lis: List<Int>, n: Int): Int = lis.binarySearch(n).let { if (it < 0) -it - 1 else it }
    }
}
