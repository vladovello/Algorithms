package frontendmasters

fun insertIntoBST(root: TreeNode?, `val`: Int): TreeNode {
    return if (root == null) TreeNode(`val`) else {
        insert(root, `val`)
        root
    }
}

private fun insert(node: TreeNode, `val`: Int) {
    if (`val` > node.`val`) {
        node.right?.let { insert(it, `val`) }.orElse {
            node.right = TreeNode(`val`)
        }
    } else if (`val` < node.`val`) {
        node.left?.let { insert(it, `val`) }.orElse {
            node.left = TreeNode(`val`)
        }
    }
}

private fun Any?.orElse(block: () -> Unit): Any? {
    if (this != null) return this
    block()
    return null
}
