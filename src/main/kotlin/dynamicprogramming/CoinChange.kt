package dynamicprogramming

import java.util.ArrayDeque
import java.util.Queue
import kotlin.math.min

interface CoinChange {

    fun coinChange(coins: IntArray, amount: Int): Int
}

object CoinChangeSolutions {
    class Solution1 : CoinChange {

        // OOM
        override fun coinChange(coins: IntArray, amount: Int): Int {
            if (amount == 0) return 0

            val remindersQueue: Queue<Int> = ArrayDeque<Int>(coins.size * 2)
            val tempQueue: Queue<Int> = ArrayDeque<Int>(coins.size)
            remindersQueue.add(amount)
            var coinCount = 0
            coins.sort()

            while (remindersQueue.isNotEmpty() || tempQueue.isNotEmpty()) {
                if (remindersQueue.isEmpty()) {
                    remindersQueue.addAll(tempQueue)
                    tempQueue.clear()
                    ++coinCount
                }
                val reminder = remindersQueue.poll()

                if (reminder % coins.last() == 0) return reminder / coins.last() + coinCount

                for (coin in coins) {
                    if (coin > reminder) break
                    if (coin == reminder) return coinCount + 1
                    tempQueue.add(reminder - coin)
                }
            }

            return -1
        }
    }

    class Solution2 : CoinChange {

        override fun coinChange(coins: IntArray, amount: Int): Int {
            val coinsCounts = IntArray(amount + 1) { -1 }
            coinsCounts[0] = 0
            coins.sort()

            for (a in 1..amount) {
                coinsCounts[a] = Int.MAX_VALUE
                for (coin in coins) {
                    if (coin > a) break
                    val rem = coinsCounts[a - coin]
                    if (rem == -1) continue
                    coinsCounts[a] = min(rem + 1, coinsCounts[a])
                }
                if (coinsCounts[a] == Int.MAX_VALUE) coinsCounts[a] = -1
            }

            return coinsCounts[amount]
        }
    }

    /**
     * Same as second but slightly more optimized by reducing branching
     */
    class Solution3 : CoinChange {

        override fun coinChange(coins: IntArray, amount: Int): Int {
            val coinsCounts = IntArray(amount + 1) { amount + 1 }
            coinsCounts[0] = 0
            coins.sort()

            for (a in 1..amount) {
                for (coin in coins) {
                    if (coin > a) break
                    val rem = coinsCounts[a - coin]
                    coinsCounts[a] = min(rem + 1, coinsCounts[a])
                }
            }

            return if (coinsCounts[amount] > amount) -1 else coinsCounts[amount]
        }
    }
}
