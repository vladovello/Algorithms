fun main() {
    val s = "  fly    me to    the moon    "
    println(LengthOfLastWord().lengthOfLastWord(s))
}

class LengthOfLastWord {
    fun lengthOfLastWord(s: String): Int {
        val trimmedS = s.trimEnd()
        var i = trimmedS.lastIndex

        while (i >= 0 && trimmedS[i] != ' ') {
            i--
        }

        return trimmedS.lastIndex - i
    }
}