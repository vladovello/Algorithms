package stack

import java.util.*
import kotlin.math.abs


fun main() {
    val s = "[([[]])]{}"

    println(ValidParentheses().isValid(s))
}

class ValidParentheses {
    fun isValid(s: String): Boolean {
        val stack = Stack<Char>()

        for (c in s) {
            if (isClosedBracket(c)) {
                if (stack.isEmpty() || !isPairedBrackets(c, stack.peek())) return false
                stack.pop()
            } else stack.push(c)
        }

        return stack.isEmpty()
    }

    private fun isPairedBrackets(c1: Char, c2: Char): Boolean {
        return abs(c1 - c2) <= 2
    }

    private fun isClosedBracket(c: Char): Boolean {
        return when (c) {
            in listOf('}', ')', ']') -> true
            else -> false
        }
    }
}