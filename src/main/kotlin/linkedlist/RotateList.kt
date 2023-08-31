package linkedlist

fun main() {
    val head = initLinkedList(intArrayOf(1, 2, 3, 4, 5))
    val newHead = RotateList().rotateRight(head, 6)
}

class RotateList {
    fun rotateRight(head: ListNode?, k: Int): ListNode? {
        if (head == null) return null

        var tTail = head
        var newTail: ListNode? = head
        var size = 1

        while (tTail?.next != null) {
            tTail = tTail.next
            ++size
        }

        if (k % size == 0) return head
        val distance = size - (k % size)

        for (i in 0 until distance - 1) {
            newTail = newTail?.next
        }

        val newHead = newTail?.next
        newTail?.next = null
        tTail?.next = head

        return newHead
    }
}