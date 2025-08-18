package dynamicprogramming

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.collections.shouldBeIn
import io.kotest.matchers.equals.shouldBeEqual

class LongestPalindromicSubstringTest : FunSpec({
    val solution = LongestPalindromicSubstring()

    context("LongestPalindromicSubstring Tests") {
        test("Input: \"babad\"") {
            val s = "babad"
            val result = solution.longestPalindrome(s)
            result shouldBeIn listOf("bab", "aba")
        }

        test("Input: \"cbbd\"") {
            val s = "cbbd"
            val result = solution.longestPalindrome(s)
            result shouldBeEqual "bb"
        }

        test("Input: \"abcddd\"") {
            val s = "abcddd"
            val result = solution.longestPalindrome(s)
            result shouldBeEqual "ddd"
        }
    }
})
