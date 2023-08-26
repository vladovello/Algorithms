package matrix

fun main() {
    val matrix = arrayOf(
        intArrayOf( 1,  2,  3,  4,  5),
        intArrayOf( 6,  7,  8,  9, 10),
        intArrayOf(11, 12, 13, 14, 15),
        intArrayOf(16, 17, 18, 19, 20),
        intArrayOf(21, 22, 23, 24, 25),
    )
    RotateImage().rotate(matrix)

    for (i in matrix.indices) {
        for (j in matrix.indices) {
            print("${matrix[i][j]} ")
        }
        println()
    }
}

class RotateImage {
//    More clever way (genius I'd say):
    fun rotate(matrix: Array<IntArray>) {
        val lastIdx = matrix.lastIndex

        for (i in 0 until (matrix.size + 1) / 2) {
            for (j in 0 until matrix.size / 2) {
                swap(matrix[lastIdx - j], i, matrix[i], j)
                swap(matrix[lastIdx - i], lastIdx - j, matrix[j], lastIdx - i)
                swap(matrix[lastIdx - j], i, matrix[j], lastIdx - i)
            }
        }
    }

//    Straight forward way:
//    fun rotate(matrix: Array<IntArray>) {
//        var leftBorder = 0
//        var rightBorder = matrix[0].size - 1
//        var topBorder = 0
//        var botBorder = matrix.size - 1
//
//        val buffer = IntArray(matrix.size)
//        var buffIdx = 0
//
//        while (leftBorder <= rightBorder && topBorder <= botBorder) {
//            for (i in botBorder downTo topBorder) {
//                buffer[buffIdx++] = matrix[i][leftBorder]
//            }
//            buffIdx = 0
//
//            for (j in leftBorder..rightBorder) {
//                swap(matrix[topBorder], j, buffer, buffIdx++)
//            }
//            ++topBorder
//            buffIdx = 1
//
//            for (i in topBorder..botBorder) {
//                swap(matrix[i], rightBorder, buffer, buffIdx++)
//            }
//            --rightBorder
//            buffIdx = 1
//
//            for (j in rightBorder downTo leftBorder) {
//                swap(matrix[botBorder], j, buffer, buffIdx++)
//            }
//            --botBorder
//            buffIdx = 1
//
//            for (i in botBorder downTo topBorder) {
//                swap(matrix[i], leftBorder, buffer, buffIdx++)
//            }
//            ++leftBorder
//            buffIdx = 0
//        }
//    }

    private fun swap(arr1: IntArray, idx1: Int, arr2: IntArray, idx2: Int) {
        arr1[idx1] = arr1[idx1] xor arr2[idx2]
        arr2[idx2] = arr1[idx1] xor arr2[idx2]
        arr1[idx1] = arr1[idx1] xor arr2[idx2]
    }
}