package frontendmasters.binarytree

fun preorderTraversal(head: TreeNode): List<Int> {
    return walk(head)
}

private fun walk(node: TreeNode): List<Int> {
    val values = mutableListOf(node.`val`)

    node.left?.let {
        values.addAll(walk(it))
    }
    node.right?.let {
        values.addAll(walk(it))
    }

    return values
}
