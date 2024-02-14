package frontendmasters.binarytree

import java.util.Stack

fun main() {
    val root = TreeNode(1).apply { right = TreeNode(2).apply { left = TreeNode(3) } }
    println(inorderTraversal(root))
}

fun inorderTraversal(root: TreeNode?): List<Int> {
    val values = mutableListOf<Int>()
    return if (root == null) values else iterativeWalk(root)
}

private fun walk(node: TreeNode, values: MutableList<Int>): List<Int> {
    node.left?.let { walk(it, values) }
    values.add(node.`val`)
    node.right?.let { walk(it, values) }

    return values
}

private fun iterativeWalk(root: TreeNode?): List<Int> {
    val stack = Stack<TreeNode>()
    val values = mutableListOf<Int>()
    var node: TreeNode? = root

    while (node != null || stack.isNotEmpty()) {
        if (node != null) {
            stack.push(node)
            node = node.left
            continue
        }

        node = stack.pop()
        values.add(node.`val`)

        if (node != null) node = node.right
    }

    return values
}

private typealias Record = Pair<TreeNode, Box<Int>>

private fun iterativeFakeStackWalk(root: TreeNode): List<Int> {
    val callStack = Stack<Record>().apply { push(root to Box(0)) }
    val values = mutableListOf<Int>()

    while (callStack.isNotEmpty()) {
        val (node, flag) = callStack.peek()

        if (flag.value == 0) {
            if (node.left != null) {
                callStack.push(node.left!! to Box(0))
                flag.value = 1
                continue
            }
            flag.value = 1
        }
        if (flag.value == 1) {
            values.add(node.`val`)
            flag.value = 2
        }
        if (flag.value == 2) {
            if (node.right != null) {
                callStack.add(node.right!! to Box(0))
                flag.value = 3
                continue
            }
            flag.value = 3
        }

        callStack.pop()
    }

    return values
}
