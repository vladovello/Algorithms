package hashmap

fun main() {
    val s = "anagram"
    val t = "nagaram"

    println(ValidAnagram().isAnagram(s, t))
}

class ValidAnagram {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val sSymbolsCount = IntArray(26)
        var matchingSymbolsRemained = 0

        for (i in s.indices) {
            sSymbolsCount[s[i] - 'a']++
            if (sSymbolsCount[s[i] - 'a'] == 1) ++matchingSymbolsRemained
        }

        val tSymbolsCount = IntArray(26)

        for (i in t.indices) {
            val tSymbolCount = tSymbolsCount[t[i] - 'a'] + 1
            val sSymbolCount = sSymbolsCount[t[i] - 'a']

            if (tSymbolCount == sSymbolCount) --matchingSymbolsRemained
            else if (tSymbolCount > sSymbolCount) return false

            tSymbolsCount[t[i] - 'a'] = tSymbolCount
        }

        return matchingSymbolsRemained == 0
    }
}