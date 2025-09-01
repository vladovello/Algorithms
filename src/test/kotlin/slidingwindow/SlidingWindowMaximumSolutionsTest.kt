package slidingwindow

import common.runTestArray
import common.runTestsInContext
import io.kotest.core.spec.style.FunSpec

class SlidingWindowMaximumSolutionsTest : FunSpec({
    testCases(SlidingWindowMaximumSolutions.Solution1())
    testCases(SlidingWindowMaximumSolutions.Solution2())
})

private fun FunSpec.testCases(solution: SlidingWindowMaximum) {
    runTestsInContext(solution) {
        test("Input: [1,3,-1,-3,5,3,6,7], 3") {
            runTestArray(intArrayOf(3, 3, 5, 5, 6, 7)) { solution.maxSlidingWindow(intArrayOf(1, 3, -1, -3, 5, 3, 6, 7), 3) }
        }
        test("Input: [1], 1") {
            runTestArray(intArrayOf(1)) { solution.maxSlidingWindow(intArrayOf(1), 1) }
        }
    }
}
