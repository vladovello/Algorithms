package contest.tinkoff

fun main() {
    val n = readlnOrNull()?.toInt() ?: 0
    val a = readlnOrNull()!!.split(" ").map { it.toInt() }.toIntArray()
    val b = readlnOrNull()!!.split(" ").map { it.toInt() }.toIntArray()
    println(canGetWinningSequence(n, a, b))
}

private const val YES = "YES"
private const val NO = "NO"

fun canGetWinningSequence(n: Int, a: IntArray, b: IntArray): String {
    var left = 0
    var right = n - 1

    while (left <= right && a[left] == b[left]) ++left
    if (left > right) return YES
    while (right > left && a[right] == b[right]) --right
    if (left == right) return NO

    val aValues = HashMap<Int, Int>(right - left + 1)
    for (i in left..right) aValues[a[i]] = aValues.getOrDefault(a[i], 0) + 1

    for (i in left..right) {
        val bCurr = b[i]
        if (i != right && bCurr > b[i + 1]) return NO

        val aValue = aValues.getOrDefault(bCurr, 0)

        if (aValue <= 0) return NO

        aValues[bCurr] = aValue - 1
    }

    return YES
}
