package linkedlist

class AddTwoNumbers {
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode {
        val tail = ListNode(0)
        var head = ListNode(Int.MIN_VALUE)
        head.next = tail

        var l1T = l1
        var l2T = l2
        var rem = 0

        while (l1T != null || l2T != null || rem != 0) {
            head = head.next!!

            val sum = (l1T?.`val` ?: 0) + (l2T?.`val` ?: 0) + rem
            rem = sum / 10

            head.`val` = sum % 10
            head.next = ListNode(0)

            l1T = l1T?.next
            l2T = l2T?.next
        }

        head.next = null

        return tail
    }
}