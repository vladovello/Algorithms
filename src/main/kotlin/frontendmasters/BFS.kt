package frontendmasters

import java.util.ArrayDeque
import java.util.Queue

fun levelOrder(root: TreeNode?): List<List<Int>> {
    return if (root == null) emptyList() else bfs(root)
}

private fun bfs(node: TreeNode): List<List<Int>> {
    val currentNodes: Queue<TreeNode> = ArrayDeque<TreeNode>().apply { add(node) }
    val levelOrder = mutableListOf<MutableList<Int>>()

    while (currentNodes.isNotEmpty()) {
        levelOrder.add(mutableListOf())

        for (i in 0..<currentNodes.size) {
            val current = currentNodes.poll()
            current.left?.let { currentNodes.offer(it) }
            current.right?.let { currentNodes.offer(it) }
            levelOrder.last().add(current.`val`)
        }
    }

    return levelOrder
}
