fun main() {
    println(ReverseWords().reverseWords("a good   example"))
}

class ReverseWords {
    fun reverseWords(s: String): String {
        val trimmedS = s.trim()
        var isWord = true

        var reversedString = ""
        var currentWord = ""

        for (i in trimmedS.lastIndex downTo 0) {
            isWord = if (trimmedS[i].isLetterOrDigit()) {
                true
            } else {
                if (!isWord) continue
                false
            }
             
            if (!isWord) {
                reversedString += currentWord
                currentWord = ""

                if (trimmedS[i].isWhitespace()) reversedString += " "
                continue
            }

            currentWord = trimmedS[i] + currentWord
        }

        reversedString += currentWord

        return reversedString
    }
}