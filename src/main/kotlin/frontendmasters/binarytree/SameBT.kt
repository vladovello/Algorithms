package frontendmasters.binarytree

fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean = dfs(p, q)

private fun dfs(p: TreeNode?, q: TreeNode?): Boolean {
    return if (p == null || q == null) p == null && q == null
    else if (p.`val` != q.`val`) false
    else dfs(p.left, q.left) && dfs(p.right, q.right)
}

private fun bfs(p: TreeNode?, q: TreeNode?): Boolean {
    if (p == null || q == null) {
        return p == null && q == null
    }

    val pCurrentNodes = ArrayDeque<TreeNode?>().apply { addLast(p) }
    val qCurrentNodes = ArrayDeque<TreeNode?>().apply { addLast(q) }

    while (pCurrentNodes.isNotEmpty() && qCurrentNodes.isNotEmpty()) {
        if (pCurrentNodes.size != qCurrentNodes.size) return false

        val size = pCurrentNodes.size
        for (i in 0..<size) {
            val pNode = pCurrentNodes.removeFirst()
            val qNode = qCurrentNodes.removeFirst()

            if (pNode == null && qNode == null) continue
            else if (pNode != null && qNode != null) {
                if (pNode.`val` != qNode.`val`) return false
            } else return false

            pCurrentNodes.offerChildren(pNode)
            qCurrentNodes.offerChildren(qNode)
        }
    }

    return true
}

private fun ArrayDeque<TreeNode?>.offerChildren(node: TreeNode) {
    addLast(node.left)
    addLast(node.right)
}
