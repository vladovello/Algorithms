package hashmap

class RansomNote {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val lettersCount = hashMapOf<Char, Int>()

        for (c in magazine) {
            lettersCount[c] = lettersCount.getOrDefault(c, 0) + 1
        }

        for (c in ransomNote) {
            val letterCount = lettersCount.getOrDefault(c, 0)

            if (letterCount == 0) return false

            lettersCount[c] = letterCount - 1
        }

        return true
    }
}