package stack

import java.util.*

class EvaluateReversePolishNotation {
    fun evalRPN(tokens: Array<String>): Int {
        val expression = Stack<Int>()

        for (token in tokens) {
            if (token !in listOf("+", "-", "*", "/")) {
                expression.push(token.toInt())
                continue
            }

            val result = calculate(expression, token)
            expression.push(result)
        }

        return expression.pop()
    }

    private fun calculate(expression: Stack<Int>, token: String): Int {
        val val1 = expression.pop()
        val val2 = expression.pop()

        return when(token) {
            "+" -> val2 + val1
            "-" -> val2 - val1
            "*" -> val2 * val1
            "/" -> val2 / val1
            else -> throw NoSuchElementException("No arithmetic operator $token exists")
        }
    }
}