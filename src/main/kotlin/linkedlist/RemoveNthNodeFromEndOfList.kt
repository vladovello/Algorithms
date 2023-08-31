package linkedlist

class RemoveNthNodeFromEndOfList {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null) return null

        var tTail = head
        for (i in 0 until n - 1) tTail = tTail?.next

        if (tTail?.next == null) return head.next

        var nthNode: ListNode = head
        var beforeNthNode: ListNode? = null

        while (true) {
            if (tTail?.next == null) {
                beforeNthNode?.next = nthNode.next
                break
            }

            beforeNthNode = nthNode
            nthNode = nthNode.next!!
            tTail = tTail?.next
        }

        return head
    }
}