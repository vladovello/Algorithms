package contest.tinkoffwinter

fun main() {
    val t = readInt()

    for (i in 0 until t) {
        val vertices = readInt()
        val degrees = readArray()

        val canMakeContact = degrees.sum() / 2 >= vertices - 1
        if (canMakeContact) println(YES) else println(NO)
    }
}

private const val YES = "Yes"
private const val NO = "No"

private fun readInt() = readlnOrNull()?.toInt()!!
private fun readArray() = readlnOrNull()?.split(" ")?.map { it.toInt() }!!
