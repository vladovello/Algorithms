package binarysearch

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.shouldBe

class SearchInRotatedSortedArrayTest : FunSpec({
    val solution = SearchInRotatedSortedArray()

    context("SearchInRotatedSortedArrayTest") {
        test("Input: [1], 1") {
            val result = solution.search(intArrayOf(1), 1)
            result shouldBeEqual 0
        }

        test("Input: [3, 1], 1") {
            val result = solution.search(intArrayOf(3, 1), 1)
            result shouldBeEqual 1
        }
    }
})
