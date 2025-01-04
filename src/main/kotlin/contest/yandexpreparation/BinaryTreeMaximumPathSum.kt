package contest.yandexpreparation

import kotlin.math.max

private class TreeNode(var `val`: Int) {
    var left: TreeNode? = null
    var right: TreeNode? = null
}

private fun maxPathSum(root: TreeNode?): Int {
    var maxSum = Int.MIN_VALUE

    fun getMaxPathSum(node: TreeNode?): Int {
        if (node == null) return 0

        val leftSum = max(getMaxPathSum(node.left), 0)
        val rightSum = max(getMaxPathSum(node.right), 0)
        val currentRootSum = node.`val` + leftSum + rightSum

        maxSum = max(maxSum, currentRootSum)

        return node.`val` + max(leftSum, rightSum)
    }

    getMaxPathSum(root)

    return maxSum
}
