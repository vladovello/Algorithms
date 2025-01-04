package contest.yandexpreparation

fun main() {
    val head = mergeKLists(
        arrayOf(
            ListNode(1).also { it.next = ListNode(4).also { it.next = ListNode(5) } },
            ListNode(1).also { it.next = ListNode(3).also { it.next = ListNode(4) } },
            ListNode(2).also { it.next = ListNode(6) },
        )
    )!!

    val expected = listOf(1, 1, 2, 3, 4, 4, 5, 6)
    var current: ListNode? = head
    var isRight = true

    for (num in expected) {
        if (current == null || current.`val` != num) {
            isRight = false
            break
        }
        current = current.next
    }

    if (isRight) println("Истина") else println("Враки")
}

private fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val nodes = buildHeap(lists)

    if (nodes.size == 0) return null

    val merged = nodes[0]
    var current = merged

    while (nodes.isNotEmpty()) {
        if (nodes[0].next != null) nodes[0] = nodes[0].next!!
        else {
            nodes.swap(0, nodes.lastIndex)
            nodes.removeLast()
        }

        heapifyDown(nodes, 0)

        current.next = nodes.firstOrNull()
        current.next?.let { current = it }
    }

    return merged
}

// 1. Построить heap (heapifyUp, heapifyDown)
// 2. Вставлять новое значение (insert)
private fun buildHeap(lists: Array<ListNode?>): MutableList<ListNode> {
    val nodes = disposeNulls(lists)
    val start = (nodes.size / 2) - 1

    for (i in start downTo 0) heapifyDown(nodes, i)

    return nodes
}

private fun heapifyDown(lists: MutableList<ListNode>, current: Int) {
    var lowest = current
    val left = 2 * current + 1
    val right = 2 * current + 2

    if (left < lists.size && lists[left].`val` < lists[lowest].`val`) lowest = left
    if (right < lists.size && lists[right].`val` < lists[lowest].`val`) lowest = right

    if (lowest != current) {
        lists.swap(current, lowest)
        heapifyDown(lists, lowest)
    }
}

private fun <E> MutableList<E>.swap(idx1: Int, idx2: Int) {
    val temp = this[idx1]
    this[idx1] = this[idx2]
    this[idx2] = temp
}

private fun disposeNulls(lists: Array<ListNode?>): MutableList<ListNode> {
    val list = ArrayList<ListNode>(lists.size)
    for (node in lists) if (node != null) list.add(node)
    return list
}

private class ListNode(var `val`: Int) {
    var next: ListNode? = null
}
