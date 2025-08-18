package frontendmasters.heap

class MinHeap<T : Comparable<T>> {

    private val nodes: MutableList<T>
    private var size: Int

    constructor() {
        this.nodes = mutableListOf()
        this.size = nodes.size
    }

    constructor(nodes: List<T>) {
        this.nodes = nodes.toMutableList()
        this.size = nodes.size
    }

    operator fun get(index: Int): T = nodes[index]

    fun parent(index: Int): T = nodes[parentIndex(index)]
    fun parentOrNull(index: Int): T? = nodes.getOrNull(parentIndex(index))

    fun leftChild(parent: Int): T? {
        val leftIdx = leftChildIndex(parent)
        return if (leftIdx < size) this[leftIdx] else null
    }

    fun rightChild(parent: Int): T? {
        val rightIdx = rightChildIndex(parent)
        return if (rightIdx < size) this[rightChildIndex(parent)] else null
    }

    fun children(parent: Int): Pair<T?, T?> = leftChild(parent) to rightChild(parent)

    fun first(): T = this[ROOT_INDEX]
    fun last(): T = this[size - 1]

    fun add(value: T) {
        nodes.add(size - 1, value)
        size++
        heapifyUp(size - 1)
    }

    fun update(index: Int, newValue: T) {
        nodes[index] = newValue
        heapifyDown(index)
        heapifyUp(index)
    }

    fun removeFirst(): T {
        return if (nodes.isEmpty()) throw NoSuchElementException()
        else removeAt(0)
    }

    fun removeAt(index: Int): T {
        checkIndex(index, size)

        val deleted = nodes[index]
        nodes[index] = last()
        size--

        heapifyDown(index)

        return deleted
    }

    override fun toString(): String {
        return nodes.subList(0, size).toString()
    }

    private fun heapifyUp(index: Int) {
        checkIndex(index, size)

        var currentIndex = index
        while (true) {
            val parentIndex = parentIndex(currentIndex)

            if (parentIndex == -1 || nodes[currentIndex] >= nodes[parentIndex]) break

            swap(currentIndex, parentIndex)
            currentIndex = parentIndex
        }
    }

    private fun heapifyDown(index: Int) {
        checkIndex(index, size)

        var currentIdx = index
        while (true) {
            val leftChildIdx = leftChildIndex(currentIdx)
            val rightChildIdx = rightChildIndex(currentIdx)

            if (leftChildIdx >= size) break

            // Should swap with the left branch
            if (rightChildIdx >= size || nodes[leftChildIdx] <= nodes[rightChildIdx]) {
                if (nodes[leftChildIdx] < nodes[currentIdx]) {
                    swap(currentIdx, leftChildIdx)
                    currentIdx = leftChildIdx
                } else break
                // Should swap with right branch
            } else {
                if (nodes[rightChildIdx] < nodes[currentIdx]) {
                    swap(currentIdx, rightChildIdx)
                    currentIdx = rightChildIdx
                } else break
            }
        }
    }

    private fun swap(index1: Int, index2: Int) {
        val temp = nodes[index1]
        nodes[index1] = nodes[index2]
        nodes[index2] = temp
    }

    private fun parentIndex(index: Int): Int = if (index == ROOT_INDEX) -1 else (index - 1) / 2
    private fun leftChildIndex(parent: Int): Int = 2 * parent + LEFT_CHILD_TERM
    private fun rightChildIndex(parent: Int): Int = 2 * parent + RIGHT_CHILD_TERM

    companion object {

        private const val ROOT_INDEX = 0
        private const val LEFT_CHILD_TERM = 1
        private const val RIGHT_CHILD_TERM = 2

        private fun checkIndex(index: Int, size: Int) {
            if (index >= size) throw IndexOutOfBoundsException("index: $index, size: $size")
        }
    }
}
