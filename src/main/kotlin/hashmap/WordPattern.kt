package hashmap

fun main() {
    val pattern = "abba"
    val s = "d0g d0g d0g d0g"

    println(WordPattern().wordPattern(pattern, s))
}

class WordPattern {
    fun wordPattern(pattern: String, s: String): Boolean {
        val splitString = s.split(" ")
        val patternOrder = hashMapOf<Char, Int>()
        val stringOrder = hashMapOf<String, Int>()

        if (splitString.size != pattern.length) return false

        for (i in splitString.indices) {
            val patternValue = patternOrder.getOrDefault(pattern[i], i)
            val stringValue = stringOrder.getOrDefault(splitString[i], i)

            if (patternValue != stringValue) return false

            if (patternValue == i) {
                patternOrder[pattern[i % pattern.length]] = patternValue
                stringOrder[splitString[i]] = stringValue
            }
        }

        return true
    }
}