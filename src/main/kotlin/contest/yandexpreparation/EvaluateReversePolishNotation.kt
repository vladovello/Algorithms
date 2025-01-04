package contest.yandexpreparation

import java.util.Stack

fun main() {
    val tokens = arrayOf("10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+")
    println(evalRPN(tokens) == 22)
}

private fun evalRPN(tokens: Array<String>): Int {
    val stack = Stack<Int>()

    for (token in tokens) {
        val number = when (token) {
            "-" -> {
                val right = stack.pop()
                val left = stack.pop()
                left - right
            }

            "+" -> stack.pop() + stack.pop()
            "*" -> stack.pop() * stack.pop()
            "/" -> {
                val right = stack.pop()
                val left = stack.pop()
                left / right
            }

            else -> token.toInt()
        }

        stack.push(number)
    }

    return stack.pop()
}
