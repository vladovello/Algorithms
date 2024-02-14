package frontendmasters.binarytree

fun isValidBST(root: TreeNode?): Boolean {
    return if (root == null) false else dfs(root, null, null)
}

private fun dfs(node: TreeNode, minLeftSubtreeParent: Int?, maxRightSubtreeParent: Int?): Boolean {
    node.left?.let { left ->
        if (
            node.`val` <= left.`val` ||
            (maxRightSubtreeParent != null && left.`val` <= maxRightSubtreeParent) ||
            !dfs(left, node.`val`, maxRightSubtreeParent)
        ) return false
    }

    node.right?.let { right ->
        if (
            node.`val` >= right.`val` ||
            (minLeftSubtreeParent != null && right.`val` >= minLeftSubtreeParent) ||
            !dfs(right, minLeftSubtreeParent, node.`val`)
        ) return false
    }

    return true
}

private fun inorderDfs(node: TreeNode?, lastValue: Box<Int?> = Box(null)): Boolean {
    if (node == null) return true

    node.left?.let { left ->
        if (!inorderDfs(left, lastValue)) return false
    }

    if (lastValue.value != null && node.`val` <= lastValue.value!!) return false
    lastValue.value = node.`val`

    node.right?.let { right ->
        if (!inorderDfs(right, lastValue)) return false
    }

    return true
}
