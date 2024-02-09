class LongestSubstringWithoutRepeatingChars {
    fun lengthOfLongestSubstring(s: String): Int {
        val usedSymbols = hashMapOf<Char, Int>()
        var longestSubstrLen = 0
        var start = 0

        for (i in s.indices) {
            if (!usedSymbols.containsKey(s[i]) || usedSymbols[s[i]]!! < start) {
                val currentLen = i - start + 1
                if (currentLen > longestSubstrLen) longestSubstrLen = currentLen
            } else {
                start = usedSymbols[s[i]]!! + 1
            }

            usedSymbols[s[i]] = i
        }

        return longestSubstrLen
    }
}