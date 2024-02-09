package contest.tinkoff

import kotlin.math.min

private val sheriffCount = hashMapOf(
    's' to 1,
    'h' to 1,
    'e' to 1,
    'r' to 1,
    'i' to 1,
    'f' to 2
)

fun main() {
    val text = readlnOrNull()!!
    val letterCount = IntArray(26)

    for (c in text) ++letterCount[c - 'a']

    var result = Int.MAX_VALUE
    for (entry in sheriffCount.entries) {
        val textLetterCount = letterCount[entry.key - 'a']

        if (textLetterCount == 0) {
            result = 0
            break
        }

        result = min(textLetterCount / entry.value, result)
    }

    println(result)
}
