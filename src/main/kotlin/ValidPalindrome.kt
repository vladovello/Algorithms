fun main() {
    println(ValidPalindrome().isPalindrome(".,"))
}

class ValidPalindrome {
    fun isPalindrome(s: String): Boolean {
        var i = 0
        var j = s.lastIndex
        var isPalindrome = true

        while (i < s.length && j > -1) {
            if (!s[i].isLetterOrDigit() || !s[j].isLetterOrDigit()) {
                if (!s[i].isLetterOrDigit()) ++i
                if (!s[j].isLetterOrDigit()) --j
                continue
            }

            if (s[i].lowercase() != s[j].lowercase()) {
                isPalindrome = false
                break
            }

            ++i
            --j
        }

        return isPalindrome
    }
}