package contest.yandexpreparation

import java.util.Stack

fun main() {

}

private class MinStack {
    private val stack = Stack<Int>()
    private val minimums = Stack<Int>()

    fun push(`val`: Int) {
        stack.push(`val`)
        if (minimums.isEmpty() || `val` <= minimums.peek()) minimums.push(`val`)
    }

    fun pop() {
        if (stack.pop() == minimums.peek()) minimums.pop()
    }

    fun top(): Int = stack.peek()

    fun getMin(): Int = minimums.peek()
}
