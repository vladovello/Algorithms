package priorityqueue

import java.util.PriorityQueue

object KthLargestElementInArraySolutions {
    class Solution {

        fun findKthLargest(nums: IntArray, k: Int): Int {
            val minHeap = PriorityQueue<Int>(k)
            for (n in nums) {
                if (minHeap.size < k) {
                    minHeap.offer(n)
                } else if (n > minHeap.peek()) {
                    minHeap.poll()
                    minHeap.offer(n)
                }
            }

            return minHeap.peek()
        }
    }
}
