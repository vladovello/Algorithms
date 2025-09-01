package contest.yandexpreparation

fun findAnagrams2(s: String, p: String): List<Int> {
    val letters = IntArray(26)
    for (c in p) ++letters[c - 'a']
    val anagramStarts = mutableListOf<Int>()

    for (i in s.indices) {
        if (i >= p.length) ++letters[s[i - p.length] - 'a']
        --letters[s[i] - 'a']
        if (letters.all { it == 0 }) anagramStarts.add(i - p.length + 1)
    }

    return anagramStarts
}

fun findAnagrams(s: String, p: String): List<Int> {
    val pLettersCount = p.fold(mutableMapOf<Char, Int>()) { map, c -> map.also { it[c] = (it[c] ?: 0) + 1 } }
    var left = 0
    var zeroedCount = 0
    val anagramStarts = mutableListOf<Int>()

    for (i in s.indices) {
        if (!pLettersCount.contains(s[i])) {
            while (left < i) pLettersCount[s[left]] = (pLettersCount[s[left++]] ?: 0) + 1
            left = i + 1
            zeroedCount = 0
            continue
        }

        val letterCount = pLettersCount[s[i]] ?: 0

        if (letterCount == 0) {
            while (s[left] != s[i]) {
                if (pLettersCount[s[left]] == 0) --zeroedCount
                pLettersCount[s[left]] = (pLettersCount[s[left]] ?: 0) + 1
                ++left
            }
            if (s[left] == s[i]) ++left
        } else {
            if (letterCount == 1) ++zeroedCount
            pLettersCount[s[i]] = letterCount - 1
        }

        if (zeroedCount == pLettersCount.size) anagramStarts.add(left)
    }

    return anagramStarts
}

private fun MutableMap<Char, Int>.clearAllLetters(str: String, from: Int, to: Int) {
    for (i in from..to) this[str[i]] = (this[str[i]] ?: 0) + 1
}
