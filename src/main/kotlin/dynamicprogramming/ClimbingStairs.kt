package dynamicprogramming

object ClimbingStairsSolutions {
    /**
     * Time Limit Exception
     */
    class Solution1 {

        fun climbStairs(n: Int): Int {
            fun countStairs(stairNumber: Int): Int {
                if (stairNumber > n) return 0
                else if (stairNumber == n) return 1
                return countStairs(stairNumber + 1) + countStairs(stairNumber + 2)
            }

            return countStairs(0)
        }
    }

    /**
     * Should memorize somehow
     */
    class Solution2 {

        fun climbStairs(n: Int): Int {
            val pathsFromStair = IntArray(n) { -1 }

            fun countStairs(stairNumber: Int): Int {
                if (stairNumber > n) return 0
                else if (stairNumber == n) return 1
                else if (pathsFromStair[stairNumber] != -1) return pathsFromStair[stairNumber]
                pathsFromStair[stairNumber] = countStairs(stairNumber + 1) + countStairs(stairNumber + 2)
                return pathsFromStair[stairNumber]
            }

            return countStairs(0)
        }
    }

    class Solution3 {

        fun climbStairs(n: Int): Int {
            val pathsFromStair = IntArray(n)
            pathsFromStair[0] = 1
            pathsFromStair[1] = 2
            for (i in 2..<n) {
                pathsFromStair[i] = pathsFromStair[i - 1] + pathsFromStair[i - 2]
            }

            return pathsFromStair.last()
        }
    }
}
