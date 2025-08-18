package dynamicprogramming

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MaximumSubarrayTest : FunSpec({

    val solution = MaximumSubarray()

    context("MaximumSubarray tests") {
        test("Input: [-4, -1, -21, -3]") {
            val nums = intArrayOf(-4, -1, -21, -3)
            val result = solution.maxSubArray(nums)
            result shouldBe -1
        }

        test("Input: [-4, -1, 5, 2]") {
            val nums = intArrayOf(-4, -1, 5, 2)
            val result = solution.maxSubArray(nums)
            result shouldBe 7
        }

        test("Input: [1, -10, 5, 2, -1, 4]") {
            val nums = intArrayOf(1, -10, 5, 2, -1, 4)
            val result = solution.maxSubArray(nums)
            result shouldBe 10
        }

        test("Single element: [5]") {
            val nums = intArrayOf(5)
            val result = solution.maxSubArray(nums)
            result shouldBe 5
        }

        test("Single negative: [-3]") {
            val nums = intArrayOf(-3)
            val result = solution.maxSubArray(nums)
            result shouldBe -3
        }

        test("All positives: [1, 2, 3]") {
            val nums = intArrayOf(1, 2, 3)
            val result = solution.maxSubArray(nums)
            result shouldBe 6
        }
    }
})
