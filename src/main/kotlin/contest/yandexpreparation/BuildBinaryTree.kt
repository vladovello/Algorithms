package contest.yandexpreparation

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import kotlin.math.max

fun main() {
    val reader = BufferedReader(InputStreamReader(System.`in`))
    val writer = BufferedWriter(OutputStreamWriter(System.out))

    val nodes = reader.readLine().split(" ").map { it.toInt() }.let { it.slice(0 until it.lastIndex) }
    val depth = buildTree(nodes)
    writer.write(depth.toString())

    reader.close()
    writer.close()
}

private fun buildTree(arrayTree: List<Int>): Int {
    val tree = mutableListOf<Int>()
    var depth = 0

    for (value in arrayTree) {
        val achievedDepth = insert(tree, value)
        depth = max(depth, achievedDepth)
    }

    return depth
}

private fun insert(
    tree: MutableList<Int>,
    insertingValue: Int,
    currentNode: Int = 0,
    achievedDepth: Int = 1
): Int {
    fun doAThing(child: Int): Int {
        if (child > tree.lastIndex) repeat(child - tree.lastIndex) { tree.add(0) }

        if (tree[child] == 0) {
            tree[child] = insertingValue
            return achievedDepth + 1
        }

        return insert(tree, insertingValue, child, achievedDepth + 1)
    }

    if (tree.size == 0) {
        tree.add(insertingValue)
        return 0
    }

    if (insertingValue > tree[currentNode]) {
        return doAThing(rightChild(currentNode))
    } else if (insertingValue < tree[currentNode]) {
        return doAThing(leftChild(currentNode))
    }

    return achievedDepth
}

private fun leftChild(parent: Int) = parent * 2 + 1
private fun rightChild(parent: Int) = parent * 2 + 2
