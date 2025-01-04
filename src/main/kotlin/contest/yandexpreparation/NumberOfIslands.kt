package contest.yandexpreparation

private fun numIslands(grid: Array<CharArray>): Int {
    var numberOfIslands = 0

    for (y in grid.indices) {
        for (x in grid[y].indices) {
            if (grid[y][x] == '0') continue

            val queue = ArrayDeque<Point>()
            queue.addFirst(Point(y, x))

            while (queue.isNotEmpty()) {
                val point = queue.removeFirst()
                val neighbors = grid.getNeighbors(point)

                for (neighbor in neighbors) {
                    if (grid[neighbor.y][neighbor.x] == '0') continue
                    grid[neighbor.y][neighbor.x] = '0'
                    queue.addLast(neighbor)
                }
            }

            ++numberOfIslands
        }
    }

    return numberOfIslands
}

private data class Point(val y: Int, val x: Int) {
    operator fun plus(other: Point) = Point(y + other.y, x + other.x)
}

private val shifts = listOf(Point(1, 0), Point(0, 1), Point(0, -1), Point(-1, 0))

// y x
// 1 0
// 0 1
// 0 -1
// -1 0
private fun Array<CharArray>.getNeighbors(point: Point): List<Point> {
    val neighbors = mutableListOf<Point>()

    for (shift in shifts) {
        val newPoint = point + shift
        if (
            newPoint.y >= 0 &&
            newPoint.x >= 0 &&
            newPoint.y < this.size &&
            newPoint.x < this[0].size &&
            this[newPoint.y][newPoint.x] == '1'
        ) neighbors.add(newPoint)
    }

    return neighbors
}
