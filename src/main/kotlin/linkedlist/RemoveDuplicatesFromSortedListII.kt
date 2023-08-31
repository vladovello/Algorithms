package linkedlist

class RemoveDuplicatesFromSortedListII {
    fun deleteDuplicates(head: ListNode?): ListNode? {
        if (head == null) return null

        var isStart = true
        var tTail = head
        var prevNode: ListNode? = null
        var result = head

        while (tTail != null) {
            if (tTail.`val` == tTail.next?.`val`) {
                while (tTail != null && tTail.`val` == tTail.next?.`val`) tTail = tTail.next

                if (isStart) result = tTail?.next
                else prevNode?.next = tTail?.next
            } else {
                prevNode = tTail
                isStart = false
            }

            tTail = tTail?.next
        }

        return result
    }
}