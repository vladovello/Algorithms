package contest.tinkoff

fun main() {
    val (_, s) = readlnOrNull()!!.split(" ").map { it.toInt() }.take(2)
    val costs = readlnOrNull()!!.split(" ").map { it.toInt() }

    var maxAffordableCost = 0

    for (cost in costs) {
        if (cost in (maxAffordableCost + 1)..s) {
            maxAffordableCost = cost
        }
    }

    println(maxAffordableCost)
}
