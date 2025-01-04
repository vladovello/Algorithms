package contest.yandexpreparation

fun main() {
    println(sortedSquares(intArrayOf(-4, -1, 0, 3, 10)))
    println(sortedSquares(intArrayOf(-7, -3, 2, 3, 11)))
    println(sortedSquares(intArrayOf(-10000, -9999, -7, -5, 0, 0, 10000)))
}

fun sortedSquares(nums: IntArray): IntArray {
    var left = 0
    var right = nums.lastIndex
    val squares = IntArray(nums.size)

    for (i in squares.indices.reversed()) {
        if (-nums[left] > nums[right]) {
            squares[i] = nums[left] * nums[left]
            ++left
        } else {
            squares[i] = nums[right] * nums[right]
            --right
        }
    }

    return squares
}
