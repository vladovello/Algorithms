package hashmap

fun main() {
    println(HappyNumber().isHappy(1999999999))
}

class HappyNumber {
    fun isHappy(n: Int): Boolean {
        val sumsRotation = hashSetOf<Int>()
        var sum = n.squaredDigitsSum()

        while (sum != 1 && !sumsRotation.contains(sum)) {
            sumsRotation.add(sum)
            sum = sum.squaredDigitsSum()
        }

        return sum == 1
    }
}

fun Int.squaredDigitsSum(): Int {
    var num = this
    var sum = 0

    while (num > 0) {
        val digit = num % 10
        sum += digit * digit
        num /= 10
    }

    return sum
}
