package contest.tinkoff

fun main() {
    val (n, m) = readlnOrNull()!!.split(" ").map { it.toInt() }.take(2)
    val bands = Bands(n)

    for (i in 0 until m) {
        val question = readlnOrNull()!!.split(" ").map { it.toInt() }

        when (question[0]) {
            1 -> {
                val spirit1 = question[1] - 1
                val spirit2 = question[2] - 1

                bands.union(spirit1, spirit2)
            }
            2 -> {
                val spirit1Root = bands.find(question[1] - 1)
                val spirit2Root = bands.find(question[2] - 1)

                if (spirit1Root == spirit2Root) println("YES") else println("NO")
            }
            3 -> {
                val spirit = question[1] - 1
                println(bands.getBandsChangesCount(spirit))
            }
        }
    }
}

class Bands(size: Int) {
    private val parent = IntArray(size) { it }
    private val parentBandsCount = IntArray(size) { 1 }
    private val bandsDiff = IntArray(size)

    fun find(spirit: Int): Int {
        if (parent[spirit] != spirit) {
            bandsDiff[spirit] += bandsDiff[parent[spirit]]
            parent[spirit] = find(parent[spirit])
        }

        return parent[spirit]
    }

    fun union(a: Int, b: Int) {
        val rootA = find(a)
        val rootB = find(b)

        if (rootA == rootB) return

        if (parentBandsCount[rootA] > parentBandsCount[rootB]) {
            parent[rootB] = rootA
            bandsDiff[rootB] = parentBandsCount[rootA] - parentBandsCount[rootB]
            ++parentBandsCount[rootA]
        } else {
            parent[rootA] = rootB
            bandsDiff[rootA] = parentBandsCount[rootB] - parentBandsCount[rootA]
            ++parentBandsCount[rootB]
        }
    }

    fun getBandsChangesCount(spirit: Int): Int {
        val rootSpirit = find(spirit)
        return parentBandsCount[rootSpirit] - bandsDiff[spirit]
    }
}
