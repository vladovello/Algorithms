package linkedlist

class ReverseNodesInKGroup {
    fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
        var traverseTail = head
        var beforeTraverseTail: ListNode? = null
        var groupNumber = 1
        var result: ListNode? = head

        while (traverseTail != null) {
            val segment = reverseInner(traverseTail, beforeTraverseTail, k)
            if (segment.size < k) {
                reverseInner(segment.head, beforeTraverseTail, k)
                return result
            }

            beforeTraverseTail = segment.tail
            traverseTail = beforeTraverseTail?.next

            if (groupNumber == 1) {
                result = segment.head
                ++groupNumber
            }
        }

        return result
    }

    private data class LinkedListSegment(val head: ListNode?, val tail: ListNode?, val size: Int)

    private fun reverseInner(head: ListNode?, prev: ListNode?, k: Int): LinkedListSegment {
        var traverseTail = head
        var beforeTraverseTail: ListNode? = prev

        var i = 1
        val reverseTail: ListNode? = traverseTail
        var reverseHead: ListNode? = traverseTail
        val beforeStart: ListNode? = beforeTraverseTail

        while (traverseTail != null && i <= k) {
            beforeTraverseTail = traverseTail
            traverseTail = traverseTail.next

            beforeTraverseTail.next = reverseHead
            reverseHead = beforeTraverseTail

            ++i
        }

        reverseTail?.next = traverseTail
        beforeStart?.next = reverseHead

        return LinkedListSegment(reverseHead, reverseTail, i - 1)
    }
}