package dfs

import common.TreeNode
import kotlin.math.max

class MaximumDepthOfBinaryTree {

    class Solution1 {

        fun maxDepth(root: TreeNode?): Int {
            return if (root == null) 0
            else dfs(root)
        }

        private fun dfs(node: TreeNode, depth: Int = 1): Int {
            if (node.left == null && node.right == null) return depth

            val leftDepth = node.left?.let { dfs(it, depth + 1) } ?: 0
            val rightDepth = node.right?.let { dfs(it, depth + 1) } ?: 0

            return max(leftDepth, rightDepth)
        }
    }

    class Solution2 {

        fun maxDepth(root: TreeNode?): Int {
            if (root == null) return 0
            val leftDepth = maxDepth(root.left)
            val rightDepth = maxDepth(root.right)
            return max(leftDepth, rightDepth) + 1
        }
    }
}
