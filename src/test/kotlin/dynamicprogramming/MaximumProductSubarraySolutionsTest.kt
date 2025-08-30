package dynamicprogramming

import common.runTestsInContext
import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.scopes.FunSpecContainerScope
import io.kotest.matchers.equals.shouldBeEqual
import kotlin.system.measureNanoTime

class MaximumProductSubarraySolutionsTest : FunSpec({
    testCases(MaximumProductSubarraySolutions.Solution1())
    testCases(MaximumProductSubarraySolutions.Solution2())
})

private fun FunSpec.testCases(solution: MaximumProductSubarray) {
    runTestsInContext(solution) {
        context(solution) {
            testCases()
        }
    }
}

context(solution: MaximumProductSubarray)
private suspend fun FunSpecContainerScope.testCases() {
    testCase(intArrayOf(0, 1, 0, 3, 2, 3), 18)
    testCase(intArrayOf(5, 1, 2, 6, 3, 4), 720)
    testCase(intArrayOf(2, 3, -2, 4), 6)
    testCase(intArrayOf(-2, 0, -1), 0)
    testCase(intArrayOf(-2, 3, -4), 24)
    testCase(intArrayOf(-1, -2, -9, -6), 108)
    testCase(intArrayOf(-2, -2, -9), 18)
}

context(solution: MaximumProductSubarray)
private suspend fun FunSpecContainerScope.testCase(input: IntArray, expected: Int) {
    test("Input: ${input.joinToString(",")}") {
        solution.runTest(input, expected)
    }
}

private fun MaximumProductSubarray.runTest(nums: IntArray, expected: Int) {
    val result: Int
    val elapsedTime = measureNanoTime {
        result = maxProduct(nums)
    }
    println("%,.6f ms".format(elapsedTime / 1e6))
    result shouldBeEqual expected
}
