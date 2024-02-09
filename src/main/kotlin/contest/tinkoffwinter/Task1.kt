package contest.tinkoffwinter

fun main() {
    val t = readInt()

    for (i in 0 until t) {
        val text = readlnOrNull()!!
        if (canMakeWord(text)) println(YES) else println(NO)
    }
}

private fun canMakeWord(text: String): Boolean {
    val lettersCount = IntArray(26)

    for (c in text) ++lettersCount[c - 'A']

    for ((key, value) in tinkoffCount.entries) {
        if (lettersCount[key - 'A'] != value) return false
    }

    return true
}

private val tinkoffCount = hashMapOf(
    'T' to 1,
    'I' to 1,
    'N' to 1,
    'K' to 1,
    'O' to 1,
    'F' to 2,
)

private const val YES = "Yes"
private const val NO = "No"

private fun readInt() = readlnOrNull()?.toInt()!!
