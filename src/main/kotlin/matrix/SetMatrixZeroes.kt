package matrix

fun main() {
    val matrix = arrayOf(
        intArrayOf(1, 2, 3, 4),
        intArrayOf(5, 0, 7, 8),
        intArrayOf(0, 10, 11, 12),
        intArrayOf(13, 14, 15, 0)
    )

    SetMatrixZeroes().setZeroes(matrix)

    for (i in matrix.indices) {
        for (j in matrix[0].indices) {
            print("${matrix[i][j]} ")
        }
        println()
    }
}

class SetMatrixZeroes {
    fun setZeroes(matrix: Array<IntArray>) {
        var isFirstRowHasZeroes = false
        var isFirstColumnHasZeroes = false

        for (j in matrix[0].indices) {
            if (matrix[0][j] == 0) {
                isFirstRowHasZeroes = true
                break
            }
        }

        for (i in matrix.indices) {
            if (matrix[i][0] == 0) {
                isFirstColumnHasZeroes = true
                break
            }
        }

        val isRowHasZeroes = fun(row: Int): Boolean {
            for (j in 0..matrix[0].lastIndex) {
                if (matrix[row][j] == 0) return true
            }

            return false
        }

        for (i in 1..matrix.lastIndex) {
            if (!isRowHasZeroes(i)) continue

            for (j in matrix[i].indices) {
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0
                }

                matrix[i][j] = 0
            }
        }

        for (j in 1..matrix[0].lastIndex) {
            if (matrix[0][j] != 0) continue

            for (i in 1..matrix.lastIndex) {
                matrix[i][j] = 0
            }
        }

        if (isFirstRowHasZeroes) {
            for (j in matrix[0].indices) {
                matrix[0][j] = 0
            }
        }

        if (isFirstColumnHasZeroes) {
            for (i in matrix.indices) {
                matrix[i][0] = 0
            }
        }
    }

    fun setZeroesWithAuxiliarySpace(matrix: Array<IntArray>) {
        val isRowFilled = BooleanArray(matrix.size)
        val isColumnFilled = BooleanArray(matrix[0].size)
        val isOriginalZero = { i: Int, j: Int ->
            matrix[i][j] == 0 && !isColumnFilled[j]
        }

        for (i in matrix.indices) {
            var rowHasZeroes = false

            if (isRowFilled[i]) {
                rowHasZeroes = true
            } else {
                for (j in matrix[0].indices) {
                    if (isOriginalZero(i, j)) {
                        rowHasZeroes = true
                        break
                    }
                }
            }

            if (!rowHasZeroes) continue

            for (j in matrix[0].indices) {
                if (!isOriginalZero(i, j)) {
                    matrix[i][j] = 0
                    continue
                }

                for (k in matrix.indices) {
                    if (k > i && isOriginalZero(k, j)) {
                        isRowFilled[k] = true
                    }

                    matrix[k][j] = 0
                }
                isColumnFilled[j] = true
            }
        }
    }
}