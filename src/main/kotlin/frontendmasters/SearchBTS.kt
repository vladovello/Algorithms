package frontendmasters

fun searchBST(root: TreeNode?, `val`: Int): TreeNode? {
    return recursive(root, `val`)
}

private fun recursive(root: TreeNode?, `val`: Int): TreeNode? {
    return if (root == null || `val` == root.`val`) root
    else if (`val` > root.`val`) searchBST(root.right, `val`)
    else searchBST(root.left, `val`)
}

private fun iterative(root: TreeNode?, `val`: Int): TreeNode? {
    var node = root

    while (node != null) {
        if (`val` == node.`val`) return node
        node = if (`val` > node.`val`) node.right else node.left
    }

    return null
}
