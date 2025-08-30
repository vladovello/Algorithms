package twopointers

import common.readResourceFileByLine
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.comparables.shouldBeLessThan
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.mpp.log
import org.junit.platform.commons.logging.LoggerFactory
import kotlin.time.DurationUnit
import kotlin.time.measureTime
import kotlin.time.toDuration

class FindDuplicateNumberSolutionsTest : FunSpec({
    val logger = LoggerFactory.getLogger(FindDuplicateNumberSolutionsTest::class.java)

    context("Solution 1") {
        val solution = FindDuplicateNumberSolutions.Solution1()

        test("Large Data") {
            executeTest(solution)
        }
    }

    context("Solution 2") {
        val solution = FindDuplicateNumberSolutions.Solution2()

        test("Large Data") {
            executeTest(solution)
        }
    }

    context("Solution 3") {
        val solution = FindDuplicateNumberSolutions.Solution3()

        test("Large Data") {
            executeTest(solution)
        }
    }
})

private fun executeTest(solution: FindDuplicateNumber) {
    val nums = readResourceFileByLine("find-duplicate-number-solutions-test/large-set-of-numbers.txt") { array ->
        array.split(",").map { it.toInt() }
    }.flatten().toIntArray()

    val result: Int
    val elapsedTime = measureTime { result = solution.findDuplicate(nums) }

    result shouldBeEqual 77496
    elapsedTime shouldBeLessThan 1000.toDuration(DurationUnit.MILLISECONDS)
    log { "Elapsed time: $elapsedTime" }
}
