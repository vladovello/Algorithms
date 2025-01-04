package contest.yandexpreparation

fun main() {

}

private fun destCity(paths: List<List<String>>): String {
    val departures = mutableListOf<String>()
    val destinations = mutableSetOf<String>()

    for (i in paths.indices) {
        departures.add(paths[i][0])
        if (departures.contains(paths[i][0])) destinations.remove(paths[i][0])
        if (!departures.contains(paths[i][1])) destinations.add(paths[i][1])
    }

    return destinations.toList().first()
}


private fun destCity2(paths: List<List<String>>): String {
    val pathsToCities = mutableMapOf<String, String>()

    for (i in paths.indices) pathsToCities[paths[i][0]] = paths[i][1]

    var path = paths[0][0]
    while (pathsToCities.contains(path)) path = pathsToCities[path]!!

    return path
}
