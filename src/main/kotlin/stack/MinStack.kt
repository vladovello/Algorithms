package stack

import java.util.*

class MinStack {
    private val values = mutableListOf<Int>()
    private val minValues = Stack<Pair<Int, Int>>()

    fun push(`val`: Int) {
        values.add(`val`)
        if (minValues.isEmpty() || `val` < minValues.last().first) minValues.add(`val` to values.lastIndex)
    }

    fun pop() {
        val last = values.removeLast()
        val min = minValues.last()
        if (last == min.first && values.size == min.second) minValues.removeLast()
    }

    fun top(): Int = values.last()

    fun getMin(): Int = minValues.last().first
}