package contest.yandexpreparation

fun main() {

}

fun isPalindrome(s: String): Boolean {
    s[1].lowercase()
    s.toCharArray()
    s[1]
    TODO()
}

private val lowercaseLettersRange = 97..122
private val uppercaseLettersRange = 65..90
private val digitsRange = 48..57
private fun isAlphanumeric(char: Char) = char.code in lowercaseLettersRange ||
        char.code in digitsRange ||
        char.code in uppercaseLettersRange
