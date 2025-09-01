package dynamicprogramming

import common.runTestOverriddenEquals
import common.runTestsInContext
import io.kotest.core.spec.style.FunSpec

class CoinChangeSolutionsTest : FunSpec({
    testCases(CoinChangeSolutions.Solution1())
})

private fun FunSpec.testCases(solution: CoinChange) {
    runTestsInContext(solution) {
        test("Input: [1, 2, 5], 11") {
            runTestOverriddenEquals(3) { solution.coinChange(intArrayOf(1, 2, 5), 11) }
        }
        test("Input: [2], 3") {
            runTestOverriddenEquals(-1) { solution.coinChange(intArrayOf(2), 3) }
        }
        test("Input: [1], 0") {
            runTestOverriddenEquals(0) { solution.coinChange(intArrayOf(1), 0) }
        }
        test("Input: [1, 6, 7, 9, 11], 13") {
            runTestOverriddenEquals(2) { solution.coinChange(intArrayOf(1, 6, 7, 9, 11), 13) }
        }
        test("Input: [1, 2, 5], 100") {
            runTestOverriddenEquals(20) { solution.coinChange(intArrayOf(1, 2, 5), 100) }
        }
        test("Input: [186, 419, 83, 408], 6249") {
            runTestOverriddenEquals(13) {
                val rt = Runtime.getRuntime()
                solution.coinChange(intArrayOf(186, 419, 83, 408), 6249)
                val totalMem = rt.totalMemory()
                val freeMem = rt.freeMemory()
                val usedMem = totalMem - freeMem

                println("Total JVM Memory: $totalMem bytes")
                println("Free JVM Memory: $freeMem bytes")
                println("Used JVM Memory: $usedMem bytes")
            }
        }
    }
}
