internal class GasStation {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var gasAmount = 0
        val prefixExpenses = IntArray(gas.size)
        var startingIndexPrefixExpense = 0
        var prevPrefixExpense = 0
        var startingIndex = -1

        for (i in gas.indices) {
            val currIncome = gas[i] - cost[i]

            prefixExpenses[i] = prevPrefixExpense

            if (gasAmount + currIncome < 0) {
                startingIndex = -1
                gasAmount = 0
            } else {
                if (startingIndex == -1) {
                    startingIndex = i
                    startingIndexPrefixExpense = prevPrefixExpense
                }

                gasAmount += currIncome
            }

            prevPrefixExpense += currIncome
        }

        if (startingIndex != -1 && gasAmount + startingIndexPrefixExpense < 0) {
            startingIndex = -1
        }

        return startingIndex
    }
}
