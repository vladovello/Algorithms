package dynamicprogramming

import kotlin.math.max

interface MaximumProductSubarray {

    fun maxProduct(nums: IntArray): Int
}

object MaximumProductSubarraySolutions {
    class Solution1 : MaximumProductSubarray {

        override fun maxProduct(nums: IntArray): Int {
            var allMax = Int.MIN_VALUE
            var currentProduct = 1
            var negativeReverse = 1

            for (i in 0..nums.lastIndex) {
                val n = nums[i]
                if (n == 0) {
                    negativeReverse = 1
                    currentProduct = 1
                    allMax = max(0, allMax)
                    continue
                }

                currentProduct *= n
                if (currentProduct <= 0) {
                    allMax = max(currentProduct / negativeReverse, allMax)
                    if (negativeReverse == 1) negativeReverse = currentProduct
                }
                allMax = max(currentProduct, allMax)
            }

            return allMax
        }
    }

    /**
     * Обходит массив дважды, но всё равно быстрее. Предположительно из-за того, что не использует деление (деление в 10-20 раз медленнее умножения)
     */
    class Solution2 : MaximumProductSubarray {

        override fun maxProduct(nums: IntArray): Int {
            var maxProduct = nums[0]

            // prefix
            var currentProduct = 1
            for (i in 0..<nums.size) {
                currentProduct *= nums[i]
                maxProduct = maxOf(currentProduct, maxProduct)
                if (currentProduct == 0) {
                    currentProduct = 1
                }
            }

            // reset currentProduct
            currentProduct = 1
            // suffix
            for (j in (nums.size - 1) downTo 0) {
                currentProduct *= nums[j]
                maxProduct = maxOf(currentProduct, maxProduct)
                if (currentProduct == 0) {
                    currentProduct = 1
                }
            }
            return maxProduct
        }
    }
}
