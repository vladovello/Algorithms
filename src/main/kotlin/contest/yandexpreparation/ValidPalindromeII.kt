package contest.yandexpreparation

fun main() {
    println(validPalindrome("sd"))
    println(validPalindrome("sfdsf"))

    println(validPalindrome2("sd"))
    println(validPalindrome2("sfdsf"))
}

private fun validPalindrome(s: String): Boolean = validPalindromeInternal(s)

private fun validPalindromeInternal(
    s: String,
    left: Int = 0,
    right: Int = s.lastIndex,
    hasDeletion: Boolean = false
): Boolean {
    var left = left
    var right = right

    while (left < right) {
        if (s[left] != s[right]) {
            if (hasDeletion) return false
            return validPalindromeInternal(s, left + 1, right, true) ||
                    validPalindromeInternal(s, left, right - 1, true)
        }
        ++left
        --right
    }

    return true
}

private fun validPalindrome2(s: String): Boolean {
    var left = 0
    var right = s.lastIndex

    while (left < right) {
        if (s[left] != s[right]) return isValidPalindromeRange(s, left + 1, right) ||
                isValidPalindromeRange(s, left, right - 1)
        ++left
        --right
    }

    return true
}

private fun isValidPalindromeRange(s: String, left: Int, right: Int): Boolean {
    var left = left
    var right = right

    while (left < right) {
        if (s[left] != s[right]) return false
        ++left
        --right
    }

    return true
}
