package contest.yandexpreparation

import kotlin.math.max
import kotlin.random.Random

fun main() {
//    println("My solution:")
//    println(solution(intArrayOf(1, 0, 1, 1, 0))) // 4
//    println(solution(intArrayOf(1, 0, 1, 1, 1))) // 5
//    println(solution(intArrayOf(1, 0, 0, 1, 1, 1))) // 4
//    println(solution(intArrayOf(1, 0, 1, 1, 0, 1, 1, 1, 0, 1))) // 6
//    println(solution(intArrayOf(1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1))) // 5
//
//    println()
//
//    println("Very clever solution:")
//    println(findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0))) // 4
//    println(findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 1))) // 5
//    println(findMaxConsecutiveOnes(intArrayOf(1, 0, 0, 1, 1, 1))) // 4
//    println(findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0, 1, 1, 1, 0, 1))) // 6
//    println(findMaxConsecutiveOnes(intArrayOf(1, 0, 1, 1, 0, 0, 1, 1, 1, 0, 1))) // 5

    val random = Random(0)

    repeat(100) {
        for (size in 1..200) {
            val arr = IntArray(size)
            for (idx in 0..<size) arr[idx] = random.nextInt(0, 2)

            val myResult = solution(arr)
            val referenceResult = findMaxConsecutiveOnes(arr)
            if (myResult != referenceResult) {
                println(
                    """Mismatch occurred!!!
                    Array: ${arr.joinToString()}
                    My result: $myResult
                    My result: $referenceResult
                    """.trimMargin()
                )
                println()
            }
        }
    }
}

private fun findMaxConsecutiveOnes(nums: IntArray): Int {
    var maxRange = 0
    var onesWithFlip = 0
    var consecutiveOnes = 0

    for (num in nums) {
        consecutiveOnes++

        if (num == 0) {
            onesWithFlip = consecutiveOnes
            consecutiveOnes = 0
        }

        maxRange = max(maxRange, consecutiveOnes + onesWithFlip)
    }

    return maxRange
}

private fun solution(arr: IntArray): Int {
    var left = 0
    var flipped = false
    var maxRange = 0
    var zeroPos = 0

    for (i in arr.indices) {
        if (arr[i] == 1) continue

        if (flipped) {
            maxRange = max(maxRange, i - left)
            left = zeroPos + 1
        } else flipped = true

        zeroPos = i
    }

    maxRange = max(maxRange, arr.size - left)

    return maxRange
}
