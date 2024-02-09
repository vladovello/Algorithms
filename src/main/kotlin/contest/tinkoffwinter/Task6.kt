package contest.tinkoffwinter

import kotlin.math.max
import kotlin.math.min

/*
6 3
2 4 6 8 10 12
? 2 5 3 0
+ 2 3 6
? 2 5 3 2

6 3
2 4 6 8 10 12
? 2 5 3 0
+ 1 6 6
? 2 5 3 2

4 4
3 5 10 1
? 2 4 2 1
+ 4 4 4
? 1 4 3 5
? 2 4 1 3
*/

fun main() {
    val (_, queryNumber) = readlnOrNull()?.split(" ")?.map { it.toInt() }!!
    val array = readlnOrNull()?.split(" ")?.map { it.toInt() }!!

    val tree = SegmentTree(array)

    for (index in 0 until queryNumber) {
        val queryString = readlnOrNull()?.split(" ")!!
        val type = queryString.first()
        val query = queryString.slice(1..queryString.lastIndex)

        when (type) {
            UPDATE_QUERY -> {
                val (left, right, value) = query.map { it.toInt() }
                tree.update(left = left, right = right, value = value)
            }

            GET_QUERY -> {
                val (left, right, k, b) = query.map { it.toInt() }
                println(tree.getMaxOfMin(left = left, right = right, k = k, b = b))
            }
        }
    }
}

private const val UPDATE_QUERY = "+"
private const val GET_QUERY = "?"

private class SegmentTree(array: List<Int>) {
    private val tree = LongArray(4 * array.size)
    private val postponeUpdates = LongArray(4 * array.size)
    private val size = array.size

    init {
        build(array = array)
    }

    private fun leftChild(index: Int): Long = tree[leftChildIndex(index)]
    private fun rightChild(index: Int): Long = tree[rightChildIndex(index)]

    fun getMaxOfMin(left: Int, right: Int, k: Int, b: Int): Long {
        return getImpl(left = left, right = right, index = 1, currentLeft = 1, currentRight = size, k = k, b = b)
    }

    fun update(left: Int, right: Int, value: Int) {
        updateImpl(left = left, right = right, value = value, index = 1, currentLeft = 1, currentRight = size)
    }

    private fun leftChildIndex(index: Int): Int = index * 2
    private fun rightChildIndex(index: Int): Int = index * 2 + 1

    private fun build(
        array: List<Int>,
        index: Int = 1,
        left: Int = 1,
        right: Int = size
    ) {
        if (left == right) {
            tree[index] = array[left - 1].toLong()
            return
        }

        val mid = (left + right) / 2

        build(array = array, index = leftChildIndex(index), left = left, right = mid)
        build(array = array, index = rightChildIndex(index), left = mid + 1, right = right)

        tree[index] = max(leftChild(index), rightChild(index))
    }

    private fun getImpl(
        left: Int, right: Int, index: Int, currentLeft: Int, currentRight: Int, k: Int, b: Int
    ): Long {
        if (left > right) return Long.MIN_VALUE

        if (currentLeft == left && currentRight == right) {
            if (left == right) return min(tree[index], calculateFunction(k = k, i = currentLeft, b = b))

            propagateToChildren(index)

            val mid = (currentLeft + currentRight) / 2
            val functionMidValue = calculateFunction(k = k, i = mid, b = b)

            if (rightChild(index) >= functionMidValue) {
                return getImpl(
                    left = max(left, mid + 1),
                    right = right,
                    index = rightChildIndex(index),
                    currentLeft = mid + 1,
                    currentRight = currentRight,
                    k = k,
                    b = b
                )
            }

            val leftGet = getImpl(
                left = left,
                right = min(right, mid),
                index = leftChildIndex(index),
                currentLeft = currentLeft,
                currentRight = mid,
                k = k,
                b = b
            )

            return max(leftGet, rightChild(index))
        }

        propagateToChildren(index)

        val mid = (currentLeft + currentRight) / 2

        val leftGet = getImpl(
            left = left,
            right = min(right, mid),
            index = leftChildIndex(index),
            currentLeft = currentLeft,
            currentRight = mid,
            k = k,
            b = b
        )
        val rightGet = getImpl(
            left = max(left, mid + 1),
            right = right,
            index = rightChildIndex(index),
            currentLeft = mid + 1,
            currentRight = currentRight,
            k = k,
            b = b
        )

        return max(leftGet, rightGet)
    }

    private fun calculateFunction(k: Int, i: Int, b: Int): Long {
        return k.toLong() * i + b
    }

    private fun updateImpl(left: Int, right: Int, value: Int, index: Int, currentLeft: Int, currentRight: Int) {
        if (left > right) return

        if (currentLeft == left && currentRight == right) {
            tree[index] += value.toLong()
            postponeUpdates[index] += value.toLong()
            return
        }

        propagateToChildren(index)

        val mid = (currentLeft + currentRight) / 2

        updateImpl(
            left = left,
            right = min(mid, right),
            value = value,
            index = leftChildIndex(index),
            currentLeft = currentLeft,
            currentRight = mid,
        )
        updateImpl(
            left = max(left, mid + 1),
            right = right,
            value = value,
            index = rightChildIndex(index),
            currentLeft = mid + 1,
            currentRight = currentRight,
        )

        tree[index] = max(leftChild(index), rightChild(index))
    }


    private fun propagateToChildren(index: Int) {
        val leftChildIndex = leftChildIndex(index)
        val rightChildIndex = rightChildIndex(index)
        val nodePendingUpdate = postponeUpdates[index]

        tree[leftChildIndex] += nodePendingUpdate
        tree[rightChildIndex] += nodePendingUpdate

        postponeUpdates[leftChildIndex] = nodePendingUpdate
        postponeUpdates[rightChildIndex] = nodePendingUpdate

        postponeUpdates[index] = 0L
    }
}
