package frontendmasters

fun postorderTraversal(head: TreeNode?): List<Number> {
    val values = mutableListOf<Number>()
    return if (head == null) values else walk(head, values)
}

private fun walk(node: TreeNode, values: MutableList<Number>): List<Number> {
    node.left?.let { walk(it, values) }
    node.right?.let { walk(it, values) }

    values.add(node.`val`)
    return values
}
