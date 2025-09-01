package slidingwindow

import java.util.Collections
import java.util.PriorityQueue

interface SlidingWindowMaximum {

    fun maxSlidingWindow(nums: IntArray, k: Int): IntArray
}

/**
 * https://leetcode.com/problems/sliding-window-maximum
 */
object SlidingWindowMaximumSolutions {

    /**
     * Долгое решение
     */
    class Solution1 : SlidingWindowMaximum {

        override fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
            val maxWindowsSums = IntArray(nums.size - k + 1) { Int.MIN_VALUE }
            val pq = PriorityQueue<Int>(k, Collections.reverseOrder())

            for (i in 0..<k) {
                pq.offer(nums[i])
            }
            maxWindowsSums[0] = pq.peek()

            for (i in 1..nums.size - k) {
                pq.remove(nums[i - 1])
                pq.offer(nums[i + k - 1])
                maxWindowsSums[i] = pq.peek()
            }

            return maxWindowsSums
        }
    }

    /**
     * В самом начале очереди всегда находится *наибольшее в данном промежутке число*. За ним идут следующие наибольшие числа в порядке убывания.
     * Таким образом, если мы будем извлекать из очереди первое число и присваивать его в массив, то у нас получатся наибольшие числа на всех промежутках.
     *
     * При этом *удаление первого числа* происходит при достижении во время итерации этого числа: `nums[start++] == queue.first()`
     */
    class Solution2 : SlidingWindowMaximum {

        override fun maxSlidingWindow(nums: IntArray, k: Int): IntArray {
            val maxWindowsNums = IntArray(nums.size - k + 1) { Int.MIN_VALUE }
            val queue = ArrayDeque<Int>()

            fun addNextMaxNum(nextMax: Int) {
                while (queue.isNotEmpty() && queue.last() < nextMax) queue.removeLast()
                queue.addLast(nextMax)
            }

            for (i in 0..<k) addNextMaxNum(nums[i])
            maxWindowsNums[0] = queue.first()

            var start = 0
            for (i in k..nums.lastIndex) {
                addNextMaxNum(nums[i])
                if (nums[start++] == queue.first()) queue.removeFirst()
                maxWindowsNums[start] = queue.first()
            }

            return maxWindowsNums
        }
    }
}
