package contest.tinkoffwinter

import kotlin.math.max

/*
6 10
1 1 1 7 1 1

2 10
2 8

10 10
3 3 3 5 7 9 11
2

8 9
3 5 2 2 9 11
1
*/

fun main() {
    val (_, maxLoan: Int) = readIntLine()
    val giftsPrices = readIntLine()
    println(calculateMaxLeftover(giftsPrices = giftsPrices, maxLoan = maxLoan))
}

private fun readIntLine(): List<Int> = readlnOrNull()?.split(" ")?.map { it.toInt() }
    ?: throw IllegalArgumentException("First line cannot be null and should contain only numbers")

private fun calculateMaxLeftover(giftsPrices: List<Int>, maxLoan: Int): Int {
    var maxBalanceLeft = maxLoan

    for (price in giftsPrices) {
        if (maxBalanceLeft < price) continue

        val withoutCurrentGift = price - 1
        val withCurrentGift = maxBalanceLeft - price

        maxBalanceLeft = max(withoutCurrentGift, withCurrentGift)
    }

    return maxBalanceLeft
}
