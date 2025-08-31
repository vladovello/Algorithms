package dynamicprogramming

import common.runTestsInContext
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlin.system.measureNanoTime

class CoinChangeSolutionsTest : FunSpec({
    testCases(CoinChangeSolutions.Solution1())
})

private fun FunSpec.testCases(solution: CoinChange) {
    runTestsInContext(solution) {
        test("Input: [1, 2, 5], 11") {
            runTest(3) { solution.coinChange(intArrayOf(1, 2, 5), 11) }
        }
        test("Input: [2], 3") {
            runTest(-1) { solution.coinChange(intArrayOf(2), 3) }
        }
        test("Input: [1], 0") {
            runTest(0) { solution.coinChange(intArrayOf(1), 0) }
        }
        test("Input: [1, 6, 7, 9, 11], 13") {
            runTest(2) { solution.coinChange(intArrayOf(1, 6, 7, 9, 11), 13) }
        }
        test("Input: [1, 2, 5], 100") {
            runTest(20) { solution.coinChange(intArrayOf(1, 2, 5), 100) }
        }
        test("Input: [186, 419, 83, 408], 6249") {
            runTest(13) {
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

private fun <T : Any> runTest(expected: T, testBlock: () -> T?) {
    val result: T?
    val elapsedTime = measureNanoTime {
        result = testBlock()
    }
    println("%,.6f ms".format(elapsedTime / 1e6))
    result.shouldNotBeNull()
    result shouldBeEqual expected
}
