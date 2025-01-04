package yandexpreparation

import kotlin.math.max

fun main() {
    println(longestSubarray3(intArrayOf(1, 1, 0, 1)))
    println(longestSubarray3(intArrayOf(0, 1, 1, 1, 0, 1, 1, 0, 1)))
    println(longestSubarray3(intArrayOf(1, 1, 1)))
}

private fun longestSubarray(nums: IntArray): Int {
    var isDeleted = false
    var hasZeroes = false
    var start = 0
    var i = 0
    var nextPos = 0
    var maxLen = 0
    var currentLen = 0

    while (start < nums.size && i < nums.size) {
        if (nums[i] == 0) {
            hasZeroes = true

            if (!isDeleted) {
                isDeleted = true
                nextPos = i + 1
            } else {
                maxLen = max(maxLen, currentLen)
                start = nextPos
                i = nextPos
                isDeleted = false
                currentLen = 0
                continue
            }
        } else currentLen++

        i++
    }

    maxLen = max(maxLen, currentLen)

    return maxLen - if (hasZeroes) 0 else 1
}

private fun longestSubarray2(nums: IntArray): Int {
    var maxLen = 0
    var currentLen = 0
    var prevLen = 0
    var hasZeroes = false

    for (num in nums) {
        if (num == 0) {
            hasZeroes = true
            maxLen = max(maxLen, prevLen + currentLen)
            prevLen = currentLen
            currentLen = 0
        } else currentLen++
    }

    maxLen = max(maxLen, prevLen + currentLen)

    return if (!hasZeroes) nums.size - 1 else maxLen
}

private fun longestSubarray3(nums: IntArray): Int {
    var maxLen = 0
    var start = 0
    var zeroesCount = 0

    for (i in nums.indices) {
        zeroesCount += 1 - nums[i]
        while (zeroesCount > 1) zeroesCount -= 1 - nums[start++]
        maxLen = max(maxLen, i - start)
    }

    return maxLen
}
