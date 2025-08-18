package dynamicprogramming

import kotlin.math.max

class LongestPalindromicSubstring {

    fun longestPalindrome(s: String): String {
        var start = 0
        var end = 0

        for (i in s.indices) {
            val odd = expandPalindrome(s, i, i)
            val even = expandPalindrome(s, i, i + 1)
            val maxLen = max(odd, even)

            if (maxLen > end - start + 1) {
                start = i - (maxLen - 1) / 2
                end = i + maxLen / 2
            }
        }
        
        return s.substring(start..end)
    }

    private fun expandPalindrome(s: String, left: Int, right: Int): Int {
        var left = left
        var right = right
        while (left >= 0 && right <= s.lastIndex && s[left] == s[right]) {
            left--
            right++
        }
        return right - left - 1
    }
}
