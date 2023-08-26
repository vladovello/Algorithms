package hashmap

fun main() {
    val nums = intArrayOf(1, 2, 0, 1)
    println(LongestConsecutiveSequence().longestConsecutive(nums))
}

class LongestConsecutiveSequence {
    class DisjointSet<T>(val size: Int) {
        data class SetValue<U>(var value: U, var setSize: Int)

        private val nodes = HashMap<T, SetValue<T>>(size)

        fun find(node: T): SetValue<T>? {
            val nodeValue = nodes[node] ?: return null

            if (node == nodeValue.value) return nodeValue

            return find(nodeValue.value)
        }

        fun union(parent: T, child: T): Boolean {
            val childValue = find(child) ?: return false
            val parentValue = find(parent) ?: return false

            parentValue.setSize += childValue.setSize
            nodes[childValue.value] = parentValue

            return true
        }

        fun add(node: T): Boolean = if (nodes.containsKey(node)) false else {
            nodes[node] = SetValue(node, 1)
            true
        }
    }

    fun longestConsecutive(nums: IntArray): Int {
        val disjointSet = DisjointSet<Int>(nums.size)
        var maxSize = 0

        for (num in nums) {
            if (disjointSet.find(num) == null) disjointSet.add(num)
            else continue

            val nextNum = disjointSet.find(num + 1)
            val prevNum = disjointSet.find(num - 1)

            if (nextNum != null) disjointSet.union(num, nextNum.value)
            if (prevNum != null) disjointSet.union(num, prevNum.value)

            val numSetSize = disjointSet.find(num)!!.setSize
            if (numSetSize > maxSize) maxSize = numSetSize
        }

        return maxSize
    }
}