package contest.yandexpreparation

fun main() {
    val array1 = charArrayOf('a', 'a', 'b', 'b', 'c', 'c', 'c')
    test(array1)
    val array2 = charArrayOf('a', 'b', 'c')
    test(array2)
}

private fun test(chars: CharArray) {
    println(compress(chars))
    println(chars)
}

private fun compress(chars: CharArray): Int {
    if (chars.size == 1) return 1

    var groupLength = 1
    var left = 0

    var i = 1
    while (i < chars.size) {
        while (i < chars.size && chars[i - 1] == chars[i]) {
            ++groupLength
            ++i
        }

        chars[left++] = chars[i - 1]

        if (groupLength > 1) {
            if (groupLength > 9) groupLength.toString().toCharArray().forEach { digit -> chars[left++] = digit }
            else chars[left++] = groupLength.digitToChar()
        }

        if (i == chars.lastIndex) chars[left++] = chars[i]

        groupLength = 1
        ++i
    }

    return left
}
