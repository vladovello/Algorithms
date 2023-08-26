fun main() {
    val str = "III"
    println(RomanToInt().romanToInt(str))
}

class RomanToInt {
    companion object {
        val romanToInt = hashMapOf(
            'I' to 1,
            'V' to 5,
            'X' to 10,
            'L' to 50,
            'C' to 100,
            'D' to 500,
            'M' to 1000
        )
    }

    fun romanToInt(s: String): Int {
        var result = 0
        var i = 0

        while (i < s.length) {
            val currentChar = romanToInt[s[i]]!!

            if (i == s.lastIndex) {
                result += romanToInt[s[i++]]!!
                continue
            }

            val nextChar = romanToInt[s[i + 1]]!!

            if (currentChar < nextChar) {
                result += nextChar - currentChar
                i += 2
            } else {
                result += currentChar
                ++i
            }
        }

        return result
    }
}