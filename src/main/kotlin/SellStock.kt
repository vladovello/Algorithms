private class SellStock {
    fun maxProfit(prices: IntArray): Int {
        var minPrice = Int.MAX_VALUE
        var maxProfit = 0

        for (i in prices.indices) {
            if (prices[i] < minPrice) minPrice = prices[i]
            if (prices[i] - minPrice > maxProfit) maxProfit = prices[i] - minPrice
        }

        return maxProfit
    }
}