package contest.yandexpreparation

import kotlin.math.abs
import kotlin.math.min

fun main() {
    println(isOneEditDifference("12312", "21312"))
    println(isOneEditDifference("12312", "2312"))
    println(isOneEditDifference("12312", "1312"))
    println(isOneEditDifference("12312", "1212"))
    println(isOneEditDifference("1212", "12312"))
}

// 1. Insert; 2. Delete; 3. Replace

// s = "12312"; t = "21312" - false
// s = "12312"; t = "2312" - true
// s = "12312"; t = "1312" - true
// s = "12312"; t = "1212" - true
// s = "1212"; t = "12312" - true
// s = "12412"; t = "12312" - true

private fun isOneEditDifference(str: String, template: String): Boolean {
    if (abs(str.length - template.length) > 1) return false

    val minLength = min(str.length, template.length)
    var left = 0
    var right = 0

    while (left < minLength && str[left] == template[left]) ++left
    while (right < minLength - left && str[str.lastIndex - right] == template[template.lastIndex - right]) ++right

    return minLength - left == right
}
