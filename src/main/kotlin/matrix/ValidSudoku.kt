package matrix

class ValidSudoku {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        val colReps = IntArray(9) { 0 }
        val sqrReps = IntArray(9) { 0 }

        for (i in 0..8) {
            var rowReps = 0

            for (j in 0..8) {
                if (board[i][j] == '.') continue

                val cellValue = 1 shl board[i][j] - '1'
                val sqrIdx = j / 3 + i / 3 * 3
                if (
                    rowReps and cellValue > 0 ||
                    colReps[j] and cellValue > 0 ||
                    sqrReps[sqrIdx] and cellValue > 0
                ) return false

                rowReps = rowReps or cellValue
                colReps[j] = colReps[j] or cellValue
                sqrReps[sqrIdx] = sqrReps[sqrIdx] or cellValue
            }
        }

        return true
    }

//    fun isValidSudoku(board: Array<CharArray>): Boolean {
//        val rowReps = HashSet<Char>()
//        val columnReps = hashMapOf<Int, HashSet<Char>>()
//        val squareReps = hashMapOf<Int, HashSet<Char>>()
//
//        for (i in board.indices) {
//            for (j in board[i].indices) {
//                if (board[i][j] == '.') continue
//
//                if (rowReps.contains(board[i][j])) return false
//                rowReps.add(board[i][j])
//
//                if (!columnReps.containsKey(j)) columnReps[j] = HashSet()
//                if (columnReps[j]!!.contains(board[i][j])) return false
//                columnReps[j]!!.add(board[i][j])
//
//                val squareIdx = j / 3 + i / 3 * board[i].size
//                if (!squareReps.containsKey(squareIdx)) squareReps[squareIdx] = HashSet()
//                if (squareReps[squareIdx]!!.contains(board[i][j])) return false
//                squareReps[squareIdx]!!.add(board[i][j])
//            }
//
//            rowReps.clear()
//        }
//
//        return true
//    }
}