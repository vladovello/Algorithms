package frontendmasters

// This solution has bad effect on the height of the tree,
// because it's simply connects the left child of the node being deleted to the leftmost child of the right child, thus
// expanding the tree by height of left child's subtree - 1 (-1 because of deleting node).
// The better approach is to make rightmost (max) child of left child a new parent and replace rightmost with its left child if exists.
// Or the same but in reverse can be done for right child. Side can be selected depending on the height of the left and right subtrees of the deleting node
fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
    if (root == null) return null

    val (parent, toDelete, side) = findWithParent(root, key) ?: return root

    val newParentChild = if (toDelete.right != null) {
        val leftmost = toDelete.right!!.getLeftmostChild()
        leftmost.left = toDelete.left
        toDelete.right
    } else toDelete.left

    if (parent == null) return newParentChild
    else {
        when (side) {
            LEFT -> parent.left = newParentChild
            RIGHT -> parent.right = newParentChild
        }
    }

    return root
}

const val LEFT = 0
const val RIGHT = 1

private fun findWithParent(root: TreeNode, key: Int): Triple<TreeNode?, TreeNode, Int>? {
    var node: TreeNode? = root
    var parent: TreeNode? = null
    var side = -1

    while (node != null) {
        if (key == node.`val`) return Triple(parent, node, side)
        parent = node
        node = if (key > node.`val`) {
            side = RIGHT
            node.right
        } else {
            side = LEFT
            node.left
        }
    }

    return null
}

private fun TreeNode.getLeftmostChild(): TreeNode {
    var result = this
    while (result.left != null) result = result.left!!
    return result
}
