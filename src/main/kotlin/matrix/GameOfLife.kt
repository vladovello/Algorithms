package matrix

import kotlin.math.max
import kotlin.math.min

class GameOfLife {
    fun gameOfLife(board: Array<IntArray>) {
        for (i in board.indices) {
            val rowMin = max(0, i - 1)
            val rowMax = min(i + 1, board.lastIndex)

            for (j in board[i].indices) {
                val columnMin = max(0, j - 1)
                val columnMax = min(j + 1, board[i].lastIndex)

                var aliveNeighbours = 0
                forEachInRange(rowMin..rowMax, columnMin..columnMax) { row, column ->
                    if (i == row && j == column) return@forEachInRange
                    if (isAlive(board, row, column)) ++aliveNeighbours
                }

                when (board[i][j]) {
                    1 -> if (isUnderPopulation(aliveNeighbours) || isOverPopulation(aliveNeighbours)) board[i][j] = 2
                    0 -> if (isReproduction(aliveNeighbours)) board[i][j] = -1
                }

                updateValues(board, i, j, rowMin, rowMax, columnMin, columnMax)
            }
        }
    }

    private fun updateValues(board: Array<IntArray>, row: Int, column: Int, rowMin: Int, rowMax: Int, columnMin: Int, columnMax: Int) {
        val rowRange: IntRange = if (row == rowMax) rowMin..rowMax
        else if (rowMax - row <= row - rowMin) rowMin..rowMin
        else return

        val columnRange: IntRange = if (column == columnMax) columnMin..columnMax
        else if (columnMax - column <= column - columnMin) columnMin..columnMin
        else return

        for (i in rowRange) {
            for (j in columnRange) {
                updateValue(board, i, j)
            }
        }
    }

    private fun updateValue(board: Array<IntArray>, row: Int, column: Int) {
        when (board[row][column]) {
            -1 -> board[row][column] = 1
            2 -> board[row][column] = 0
        }
    }

    private fun isAlive(board: Array<IntArray>, row: Int, column: Int) = board[row][column] >= 1
    private fun isUnderPopulation(aliveNeighbours: Int) = aliveNeighbours < 2
    private fun isOverPopulation(aliveNeighbours: Int) = aliveNeighbours > 3
    private fun isReproduction(aliveNeighbours: Int) = aliveNeighbours == 3
}

fun forEachInRange(
    rowRange: IntRange,
    columnRange: IntRange,
    action: (i: Int, j: Int) -> Unit
) {
    for (i in rowRange) {
        for (j in columnRange) {
            action(i, j)
        }
    }
}