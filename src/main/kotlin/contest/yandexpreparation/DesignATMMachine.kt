package contest.yandexpreparation

import kotlin.math.min

fun main() {
    val actions = arrayOf<String>("ATM", "deposit", "deposit", "withdraw", "withdraw", "withdraw", "withdraw")
    val arguments = arrayOf<Any>(
        Any(),
        intArrayOf(250796, 638723, 691758, 845522, 938973),
        intArrayOf(215317, 848628, 182949, 784609, 30472),
        701035245,
        109992310,
        755819795,
        722349970
    )
    val result = Array<IntArray?>(actions.size) { null }
    lateinit var atm: ATM

    for (i in actions.indices) {
        val step = when (actions[i]) {
            "ATM" -> {
                atm = ATM()
                null
            }

            "deposit" -> {
                atm.deposit(arguments[i] as IntArray)
                null
            }

            "withdraw" -> atm.withdraw(arguments[i] as Int)
            else -> null
        }

        result[i] = step
    }

    result.forEach { println(it?.joinToString()) }

//    val atm1 = ATM()
//    atm1.deposit(intArrayOf(0, 0, 1, 2, 1))
//    println(atm1.withdraw(600).joinToString())
//    atm1.deposit(intArrayOf(0, 1, 0, 1, 1))
//    println(atm1.withdraw(600).joinToString())
//    println(atm1.withdraw(550).joinToString())
//
//    val atm2 = ATM()
//    atm2.deposit(intArrayOf(0, 10, 0, 3, 0))
//    println(atm2.withdraw(500).joinToString())
}

internal class ATM {
    private val banknotesCounts = createBanknotesArray()

    fun deposit(banknotesCount: IntArray) {
        for (i in banknotesCounts.indices) banknotesCounts[i] += banknotesCount[i]
    }

    fun withdraw(amount: Int): IntArray {
        var remainingWithdrawSum = amount
        val banknotesWithdrawn = createBanknotesArray()

        for (i in banknotesCounts.indices.reversed()) {
            val denomination = resolveDenomination(i)
            val banknotesCount = min(remainingWithdrawSum / denomination, banknotesCounts[i])
            if (banknotesCount <= 0) continue

            remainingWithdrawSum -= denomination * banknotesCount
            banknotesWithdrawn[i] += banknotesCount

            if (remainingWithdrawSum == 0) {
                for (j in banknotesCounts.indices) banknotesCounts[j] -= banknotesWithdrawn[j]
                return banknotesWithdrawn
            }
        }

        return intArrayOf(-1)
    }

    private fun createBanknotesArray() = IntArray(5) { 0 }

    private fun resolveDenomination(banknoteIndex: Int) = when (banknoteIndex) {
        0 -> 20
        1 -> 50
        2 -> 100
        3 -> 200
        4 -> 500
        else -> 9999
    }
}
