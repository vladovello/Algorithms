fun main() {
    val s = "ADOBECODEBANC"
    val t = "ABC"

    println(MinimumWindowSubstring().minWindow(s, t))
}

class MinimumWindowSubstring {
    fun minWindow(s: String, t: String): String {
        val charsMap = createMapOfCharacters(t)
        var counter = 0
        var left = 0
        var minSubstrIdx = 0 to -1

        for (i in s.indices) {
            if (!charsMap.containsKey(s[i])) continue

            if (charsMap[s[i]]!! > 0) ++counter
            charsMap[s[i]] = charsMap[s[i]]!! - 1

            while (counter == t.length) {
                if (minSubstrIdx.second == -1 || i - left < minSubstrIdx.second - minSubstrIdx.first) {
                    minSubstrIdx = left to i
                }

                val currentChar = s[left++]
                if (!charsMap.containsKey(currentChar)) continue

                charsMap[currentChar] = charsMap[currentChar]!! + 1
                if (charsMap[currentChar]!! > 0) --counter
            }
        }

        return s.substring(minSubstrIdx.first..minSubstrIdx.second)
    }

    private fun createMapOfCharacters(s: String): MutableMap<Char, Int> {
        val charsMap: HashMap<Char, Int> = hashMapOf()
        for (i in s.indices) charsMap.merge(s[i], 1, Int::plus)
        return charsMap
    }
}