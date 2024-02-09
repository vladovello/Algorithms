class IsSubsequence {
    fun isSubsequence(s: String, t: String): Boolean {
        if (s.isEmpty()) return false

        var j = 0

        for (i in t.indices) {
            if (s[j] == t[i]) ++j
            if (j == s.length) return true
        }

        return false
    }
}