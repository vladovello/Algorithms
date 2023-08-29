package linkedlist

class MergeTwoSortedLists {
    fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
        if (list1 == null) return list2
        else if (list2 == null) return list1

        var head: ListNode? = null
        var tail: ListNode? = null

        var lowerNode: ListNode?
        var greaterNode: ListNode?

        if (list1.`val` < list2.`val`) {
            lowerNode = list1
            greaterNode = list2
        } else {
            lowerNode = list2
            greaterNode = list1
        }

        while (lowerNode != null && greaterNode != null) {
            if (lowerNode.`val` > greaterNode.`val`) {
                val temp = lowerNode
                lowerNode = greaterNode
                greaterNode = temp
            }

            if (tail == null) {
                tail = lowerNode
                head = tail
            } else {
                tail.next = lowerNode
                tail = tail.next
            }

            lowerNode = lowerNode.next
        }

        while (greaterNode != null) {
            if (tail == null) {
                tail = greaterNode
                head = tail
            } else {
                tail.next = greaterNode
                tail = tail.next
            }

            greaterNode = greaterNode.next
        }

        return head
    }
}