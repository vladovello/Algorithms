fun main() {
    val prices = intArrayOf(7, 1, 5, 5, 6, 3, 6, 4) // 8
    println(SellStock2().maxProfit(prices))
}

private class SellStock2 {
    fun maxProfit(prices: IntArray): Int {
        var maxProfit = 0
        var currMaxProfit = 0
        var currMinPrice = Int.MAX_VALUE

        for (price in prices) {
            val profit = price - currMinPrice

            if (profit > currMaxProfit) {
                maxProfit -= currMaxProfit
                currMaxProfit = profit
                maxProfit += currMaxProfit
            } else if (profit < currMaxProfit) {
                currMinPrice = price
                currMaxProfit = 0
            }
        }

        return maxProfit
    }
}