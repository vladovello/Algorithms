package contest.tinkoffwinter

import kotlin.math.roundToInt
import kotlin.math.sqrt

/*
5 5 5
1 2 3 4 5
1 2
2 3
3 4
4 5
5 1
? 1
? 5
+ 1 2
? 1
? 5
*/

fun main() {
    val (childrenNumber, connectionsNumber, queriesNumber) = readArray()
    computeQueries(
        childrenNumber = childrenNumber,
        connectionsNumber = connectionsNumber,
        queriesNumber = queriesNumber
    )
}

private fun readArray() = readlnOrNull()?.split(" ")?.map { it.toInt() }!!

private const val UPDATE_QUERY = "+"
private const val GET_QUERY = "?"

private fun computeQueries(childrenNumber: Int, connectionsNumber: Int, queriesNumber: Int) {
    val manulGraph = ManulGraph(childNumber = childrenNumber, connectionsNumber = connectionsNumber)
    val deltas = LongArray(childrenNumber)

    for (i in 0 until queriesNumber) {
        val queryString = readlnOrNull()?.split(" ")!!
        val type = queryString.first()
        val query = queryString.slice(1..queryString.lastIndex)

        when (type) {
            UPDATE_QUERY -> {
                val (index, manuls) = query.map { it.toInt() }
                manulGraph.updateQuery(index = index - 1, manuls = manuls, deltas = deltas)
            }
            GET_QUERY -> {
                val index = query.last().toInt()
                manulGraph.getQuery(index = index - 1, deltas = deltas)
            }
        }
    }
}

private fun ManulGraph.updateQuery(index: Int, manuls: Int, deltas: LongArray) {
    updateHeavyNodes(index = index, manuls = manuls)

    if (isHeavyNode(index)) {
        deltas[index] += manuls.toLong()
        return
    }

    updateLightNodes(index = index, manuls = manuls)
}

private fun ManulGraph.getQuery(index: Int, deltas: LongArray) {
    var sum = getManulsCount(index)

    if (isHeavyNode(index)) {
        println(sum)
        return
    }

    for (neighbour in getNodeNeighbours(index)) {
        sum += deltas[neighbour]
    }

    println(sum)
}

private fun ManulGraph(childNumber: Int, connectionsNumber: Int): ManulGraph {
    val manulGraph = ManulGraph(childNumber)
    readlnOrNull()?.split(" ")?.mapIndexed { index, manuls ->
        manulGraph.setManulsCount(index = index, manulsCount = manuls.toLong())
    }

    for (i in 0 until connectionsNumber) {
        val (node1, node2) = readArray()
        manulGraph.addConnection(node1 = node1 - 1, node2 = node2 - 1)
    }

    manulGraph.distributeByDegree()

    return manulGraph
}

private class ManulGraph(size: Int) {
    data class Node(var degree: Long = 0, var manulsCounted: Long = 0, val neighbours: MutableList<Int> = mutableListOf())

    private val nodes = Array(size) { Node() }
    private val heavyNeighbours = Array(size) { mutableListOf<Int>() }
    private val lightNeighbours = Array(size) { mutableListOf<Int>() }
    private val maxLightDegree = sqrt(size.toFloat()).roundToInt()

    fun getManulsCount(index: Int) = nodes[index].manulsCounted

    fun setManulsCount(index: Int, manulsCount: Long) {
        nodes[index].manulsCounted = manulsCount
    }

    fun getNodeNeighbours(index: Int): List<Int> = nodes[index].neighbours

    fun addConnection(node1: Int, node2: Int) {
        nodes[node1].apply {
            neighbours.add(node2)
            ++degree
        }

        nodes[node2].apply {
            neighbours.add(node1)
            ++degree
        }
    }

    fun isHeavyNode(index: Int) = nodes[index].degree > maxLightDegree

    fun updateHeavyNodes(index: Int, manuls: Int) {
        for (neighbour in heavyNeighbours[index]) {
            nodes[neighbour].manulsCounted += manuls
        }
    }

    fun updateLightNodes(index: Int, manuls: Int) {
        for (neighbour in lightNeighbours[index]) {
            nodes[neighbour].manulsCounted += manuls
        }
    }

    fun distributeByDegree() {
        for (index in 0 until nodes.size) {
            for (neighbour in nodes[index].neighbours) {
                if (isHeavyNode(neighbour)) {
                    heavyNeighbours[index].add(neighbour)
                    continue
                }

                lightNeighbours[index].add(neighbour)
            }
        }
    }
}
