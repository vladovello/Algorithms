import kotlin.math.min

fun main() {
    val strs = arrayOf("flower", "flow", "flight")
    println(LongestCommonPrefix().longestCommonPrefix(strs))
}

class LongestCommonPrefix {
    fun longestCommonPrefix(strs: Array<String>): String {
        var longestCommonPrefix = strs[0]

        for (i in 1..strs.lastIndex) {
            if (longestCommonPrefix.isEmpty()) break

            val currentStr = strs[i]
            val minLen = min(currentStr.length, longestCommonPrefix.length)
            var endIdx = 0

            for (j in 0 until minLen) {
                endIdx = j
                if (currentStr[j] != longestCommonPrefix[j]) break
            }

            longestCommonPrefix = currentStr.substring(0, endIdx)
        }

        return longestCommonPrefix
    }
}