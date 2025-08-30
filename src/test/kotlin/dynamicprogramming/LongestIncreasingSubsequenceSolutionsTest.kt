package dynamicprogramming

import common.runTestsInContext
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import kotlin.system.measureNanoTime

class LongestIncreasingSubsequenceSolutionsTest : FunSpec({
    testCases(LongestIncreasingSubsequenceSolutions.BruteForceSolution())
    testCases(LongestIncreasingSubsequenceSolutions.BinarySearchSolution())
})

private fun FunSpec.testCases(solution: LongestIncreasingSubsequence) {
    runTestsInContext(solution) {
        test("Input: [0,1,0,3,2,3]") {
            solution.runTest(intArrayOf(0, 1, 0, 3, 2, 3), 4)
        }
        test("Input: [5,1,2,6,3,4]") {
            solution.runTest(intArrayOf(5, 1, 2, 6, 3, 4), 4)
        }
        test("Input: [7,7,7,7,7,7,7]") {
            solution.runTest(intArrayOf(7, 7, 7, 7, 7, 7, 7), 1)
        }
        test("Input: [10,9,2,5,3,7,101,18]") {
            solution.runTest(intArrayOf(10, 9, 2, 5, 3, 7, 101, 18), 4)
        }
    }
}

private fun LongestIncreasingSubsequence.runTest(nums: IntArray, expected: Int) {
    val result: Int
    val elapsedTime = measureNanoTime {
        result = lengthOfLIS(nums)
    }
    println("%,.6f ms".format(elapsedTime / 1e6))
    result shouldBeEqual expected
}
