package contest.yandexpreparation

import frontendmasters.binarytree.TreeNode

private fun findDuplicateSubtrees(root: TreeNode?): List<TreeNode?> {
    val trackedNodes = mutableMapOf<String, Boolean>()
    val duplicates = mutableListOf<TreeNode?>()

    dfs(root, trackedNodes, duplicates)

    return duplicates
}

// Можно ускорить если проиндексировать узлы, чтобы избежать использования строк
private fun dfs(
    node: TreeNode?,
    existingSubtrees: MutableMap<String, Boolean>,
    duplicates: MutableList<TreeNode?>
): String {
    if (node == null) return "()"

    val subtreeSb = StringBuilder("(")
        .append(node.`val`)
        .append(dfs(node.left, existingSubtrees, duplicates))
        .append(dfs(node.right, existingSubtrees, duplicates))
        .append(")")

    val subtree = subtreeSb.toString()

    val isUsed = existingSubtrees[subtree]
    if (isUsed != null) {
        if (!isUsed) {
            duplicates.add(node)
            existingSubtrees[subtree] = true
        }
    } else existingSubtrees[subtree] = false

    return subtree
}
