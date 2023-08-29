package linkedlist

class CopyListWithRandomPointer {
    fun copyRandomList(node: Node?): Node? {
        val oldPointersToNew = hashMapOf<Node, Node>()

        fun getOrPut(node: Node): Node = oldPointersToNew[node] ?: run {
            val newNode = Node(node.`val`)
            oldPointersToNew[node] = newNode
            newNode
        }

        var head: Node? = null
        var tail: Node? = null

        var nodeTraverse = node

        while (nodeTraverse != null) {
            if (tail == null) {
                tail = Node(nodeTraverse.`val`)
                oldPointersToNew[nodeTraverse] = tail
                head = tail
            }

            if (nodeTraverse.next != null) tail.next = getOrPut(nodeTraverse.next!!)
            if (nodeTraverse.random != null) tail.random = getOrPut(nodeTraverse.random!!)

            nodeTraverse = nodeTraverse.next
            tail = tail.next
        }

        return head
    }
}