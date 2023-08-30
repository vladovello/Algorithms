package linkedlist

class ReverseLinkedListII {
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        var i = 1
        var reverseTail: ListNode? = null
        var reverseHead: ListNode? = null
        var beforeStart: ListNode? = null
        var traverseTail = head

        while (traverseTail != null && i <= right) {
            if (i == left - 1) beforeStart = traverseTail
            else if (i == left) {
                reverseTail = traverseTail
                reverseHead = traverseTail
            } else if (i !in left + 1..right) {
                ++i
                traverseTail = traverseTail.next
                continue
            }

            val temp = traverseTail
            traverseTail = traverseTail.next

            temp.next = reverseHead
            reverseHead = temp

            ++i
        }

        reverseTail?.next = traverseTail
        beforeStart?.next = reverseHead

        return if (left == 1) reverseHead else head
    }
}

fun initLinkedList(intArray: IntArray): ListNode {
    val head = ListNode(intArray[0])
    var tail = head

    for (i in 1 until intArray.size) {
        tail.next = ListNode(intArray[i])
        tail = tail.next!!
    }

    return head
}