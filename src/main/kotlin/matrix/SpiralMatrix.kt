package matrix

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 6, 7, 8),
        intArrayOf(9, 10, 11, 12)
    )
    println(SpiralMatrix().spiralOrder(matrix))
}

class SpiralMatrix {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val rowSize = matrix[0].size
        val colSize = matrix.size
        val elemsCount = rowSize * colSize
        val result = mutableListOf<Int>()

        var padding = 0

        while (result.size != elemsCount) {
            for (j in padding until rowSize - padding) {
                result.add(matrix[padding][j])
            }

            if (result.size == elemsCount) return result

            for (i in padding + 1 until colSize - padding) {
                result.add(matrix[i][rowSize - padding - 1])
            }

            if (result.size == elemsCount) return result

            for (j in rowSize - padding - 2 downTo padding) {
                result.add(matrix[colSize - padding - 1][j])
            }

            if (result.size == elemsCount) return result

            for (i in colSize - padding - 2 downTo padding + 1) {
                result.add(matrix[i][padding])
            }

            ++padding
        }

        return result
    }
}