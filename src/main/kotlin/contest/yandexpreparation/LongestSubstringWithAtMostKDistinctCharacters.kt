package contest.yandexpreparation

import kotlinx.coroutines.MainScope
import kotlin.math.max

fun main() {
    println(longestSubstring("eceba", 2))
    println(longestSubstring("aa", 1))
    println(longestSubstring2("eceba", 2))
    println(longestSubstring2("aa", 1))
}

// probably not working
private fun longestSubstring(str: String, k: Int): Int {
    if (k == 0) return 0

    val charsInSubstring = hashMapOf<Char, Int>()
    var left = 0
    var maxLen = 0

    for (i in str.indices) {
        val currentCharCount = charsInSubstring.getOrDefault(str[i], 0)
        if (currentCharCount != 0 || charsInSubstring.size < k) charsInSubstring[str[i]] = currentCharCount + 1
        else {
            maxLen = max(maxLen, i - left)
            ++left
        }
    }

    maxLen = max(maxLen, str.length - left)

    return maxLen
}

private fun longestSubstring2(str: String, k: Int): Int {
    if (k == 0) return 0

    val charsInSubstring = hashMapOf<Char, Int>()
    var left = 0
    var maxLen = 0

    for (right in str.indices) {
        charsInSubstring[str[right]] = (charsInSubstring[str[right]] ?: 0) + 1

        while (charsInSubstring.size > k) {
            val currentCharCount = charsInSubstring.getOrDefault(str[left], 0)
            if (currentCharCount == 1) charsInSubstring.remove(str[left])
            else charsInSubstring[str[left]] = currentCharCount - 1
            ++left
        }

        maxLen = max(maxLen, right - left + 1)
    }


    return maxLen
}
