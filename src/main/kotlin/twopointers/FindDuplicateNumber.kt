package twopointers

fun interface FindDuplicateNumber {

    fun findDuplicate(nums: IntArray): Int
}

object FindDuplicateNumberSolutions {

    // 2 3 4 2 1
    class Solution1 : FindDuplicateNumber {

        override fun findDuplicate(nums: IntArray): Int {
            var slowPtr = nums.lastIndex
            var fastPtr = nums.lastIndex
            while (true) {
                slowPtr = movePtr(nums, slowPtr)
                fastPtr = movePtr(nums, movePtr(nums, fastPtr))

                if (slowPtr == fastPtr) break
            }

            var finderPtr = nums.lastIndex
            while (slowPtr != finderPtr) {
                slowPtr = movePtr(nums, slowPtr)
                finderPtr = movePtr(nums, finderPtr)
            }

            return finderPtr + 1
        }

        private inline fun movePtr(nums: IntArray, ptr: Int): Int = nums[ptr] - 1
    }

    // Not uses constant memory
    class Solution2 : FindDuplicateNumber {

        override fun findDuplicate(nums: IntArray): Int {
            val tracker = IntArray(nums.size) { 0 }

            for (i in nums.indices) {
                if (++tracker[nums[i]] == 2) {
                    return nums[i]
                }
            }

            return 0
        }
    }

    // Not uses constant memory
    class Solution3 : FindDuplicateNumber {

        override fun findDuplicate(nums: IntArray): Int {
            return IntArray(nums.size).let { duplicates -> nums.find { ++duplicates[it] == 2 } ?: 0 }
        }
    }
}
