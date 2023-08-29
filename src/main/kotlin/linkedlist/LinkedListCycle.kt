package linkedlist

fun main() {
    
}

class LinkedListCycle {
    fun hasCycle(head: ListNode?): Boolean {
        var turtle = head
        var hare = head?.next

        while (hare != null) {
            if (hare == turtle) return true

            turtle = turtle?.next
            hare = hare.next?.next
        }

        return false
    }
}