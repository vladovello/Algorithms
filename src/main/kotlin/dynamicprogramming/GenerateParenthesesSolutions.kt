package dynamicprogramming

object GenerateParenthesesSolutions {
    class Solution1 {

        // (((()))) (()()()) ((()))() (())(()) ()((())) (())()()
        // 1. ()  0(1)
        // 2. ()()  0(1)(2)
        // 3. (())()  0(1(2))3(4)
        // 4. (()())()  0(1(2)(3))4(5)
        // 5. ((())())()  0(1(2(3))4(5))6(7)
        fun generateParenthesis(n: Int): List<String> {
            val result = HashSet<String>(n)
            traverseAllParenthesisVariants(n, 0, result)
            return result.toList()
        }

        private fun traverseAllParenthesisVariants(
            n: Int,
            position: Int,
            result: MutableSet<String>,
            currentParenthesis: StringBuilder = StringBuilder(n),
        ) {
            currentParenthesis.insert(position, "()")

            if (currentParenthesis.length / 2 == n) {
                result.add(currentParenthesis.toString())
                return
            }

            for (i in currentParenthesis.indices) {
                traverseAllParenthesisVariants(n, i, result, currentParenthesis)
                currentParenthesis.deleteRange(i, i + 2)
            }
        }
    }

    class Solution2 {

        fun generateParenthesis(n: Int): List<String> {
            val result = mutableListOf<String>()
            val parentheses = CharArray(n * 2)

            fun backtrack(position: Int, openCount: Int, closedCount: Int) {
                if (openCount + closedCount == n * 2) {
                    result.add(parentheses.concatToString())
                    return
                }

                if (openCount < n) {
                    parentheses[position] = '('
                    backtrack(position + 1, openCount + 1, closedCount)
                }
                if (closedCount < openCount) {
                    parentheses[position] = ')'
                    backtrack(position + 1, openCount, closedCount + 1)
                }
            }

            backtrack(0, 0, 0)
            return result
        }
    }
}
