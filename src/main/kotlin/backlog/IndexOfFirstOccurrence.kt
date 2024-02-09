fun main() {
    val haystack = "sadbutsad"
    val needle = "sad"
    println(IndexOfFirstOccurrence().strStr(haystack, needle))
}

class IndexOfFirstOccurrence {
    fun strStr(haystack: String, needle: String): Int {
        var startIdx = -1

        for (i in haystack.indices) {
            if (haystack[i] != needle[0]) continue

            startIdx = i
            for (j in needle.indices) {
                if (i + j >= haystack.length || haystack[i + j] != needle[j]) {
                    startIdx = -1
                    break
                }
            }

            if (startIdx != -1) return startIdx
        }

        return startIdx
    }
}