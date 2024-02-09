package contest.tinkoff

import kotlin.math.min

fun main() {
    val (n, m) = readlnOrNull()!!.split(" ").map { it.toInt() }.take(2)
    val citiesConnections = ArrayList<CitiesConnection>(m)

    for (i in 0 until m) {
        val connectionString = readlnOrNull()!!.split(" ").map { it.toInt() }
        citiesConnections.add(CitiesConnection(connectionString[0], connectionString[1], connectionString[2]))
    }

    println(chooseBestRoadLengthToDestroy(n, citiesConnections))
}

data class CitiesConnection(val city1: Int, val city2: Int, val roadLength: Int)

fun chooseBestRoadLengthToDestroy(numberOfCities: Int, citiesConnections: MutableList<CitiesConnection>): Int {
    citiesConnections.sortWith(compareByDescending { citiesConnection -> citiesConnection.roadLength })

    val dsu = DisjointSet(numberOfCities)
    var x = Int.MAX_VALUE

    for (connection in citiesConnections) {
        val city1parent = dsu.find(connection.city1 - 1)
        val city2parent = dsu.find(connection.city2 - 1)

        if (city1parent == city2parent) continue

        dsu.union(city1parent, city2parent)

        x = min(x, connection.roadLength) - 1
    }

    return x
}

class DisjointSet(val size: Int) {
    private val parent = IntArray(size) { it }
    private val rank = IntArray(size)

    fun find(elem: Int): Int {
        if (parent[elem] != elem) parent[elem] = find(parent[elem])
        return parent[elem]
    }

    fun union(a: Int, b: Int) {
        val rootA = find(a)
        val rootB = find(b)

        if (rank[rootA] > rank[rootB]) parent[rootB] = rootA
        else if (rank[rootB] > rank[rootA]) parent[rootA] = rootB
        else {
            parent[rootA] = rootB
            ++rank[rootB]
        }
    }
}
