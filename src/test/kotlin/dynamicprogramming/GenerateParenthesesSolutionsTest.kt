package dynamicprogramming

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual

class GenerateParenthesesSolutionsTest : FunSpec({

    context("Solution 1") {
        val solution1 = GenerateParenthesesSolutions.Solution1()

        test("Input: n = 1") {
            val result = solution1.generateParenthesis(1)
            result.toSet() shouldBeEqual setOf("()")
        }

        test("Input: n = 2") {
            val result = solution1.generateParenthesis(2)
            result.toSet() shouldBeEqual setOf("()()", "(())")
        }

        test("Input: n = 3") {
            val result = solution1.generateParenthesis(3)
            result.toSet() shouldBeEqual setOf("((()))", "(()())", "(())()", "()(())", "()()()")
        }
    }

    context("Solution 2") {
        val solution2 = GenerateParenthesesSolutions.Solution2()

        test("Input: n = 1") {
            val result = solution2.generateParenthesis(1)
            result.toSet() shouldBeEqual setOf("()")
        }

        test("Input: n = 2") {
            val result = solution2.generateParenthesis(2)
            result.toSet() shouldBeEqual setOf("()()", "(())")
        }

        test("Input: n = 3") {
            val result = solution2.generateParenthesis(3)
            result.toSet() shouldBeEqual setOf("((()))", "(()())", "(())()", "()(())", "()()()")
        }
    }

    fun compareParenthesis(expected: Set<String>, actual: List<String>) {
        actual.toSet() shouldBeEqual expected
    }
})
