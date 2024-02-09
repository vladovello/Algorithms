fun main() {
    val num = 1
    println(IntegerToRoman().intToRoman(num))
}

class IntegerToRoman {
    companion object {
        val intRoman = hashMapOf(
            1 to "I",
            5 to "V",
            10 to "X",
            50 to "L",
            100 to "C",
            500 to "D",
            1000 to "M"
        )
    }

    fun intToRoman(num: Int): String {
        var romanNumber = ""
        var k = 1

        while (k <= num) {
            val digit = num % (10 * k) - num % k
            val midValue = k * 5

            romanNumber = if (digit < midValue) {
                if (digit == midValue - k) intRoman[k]!! + intRoman[midValue]!! + romanNumber
                else intRoman[k]!!.repeat(digit / k) + romanNumber
            } else if (digit == midValue) {
                intRoman[midValue]!! + romanNumber
            } else if (digit < k * 9) {
                intRoman[midValue]!! + intRoman[k]!!.repeat((digit - midValue) / k) + romanNumber
            } else {
                intRoman[k]!! + intRoman[k * 10]!! + romanNumber
            }

            k *= 10
        }

        return romanNumber
    }
}