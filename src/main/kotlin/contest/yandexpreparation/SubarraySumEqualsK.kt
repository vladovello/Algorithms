package contest.yandexpreparation

fun main() {
    println(subarraySum(intArrayOf(1, 1, 1), 2)) // 2
    println(subarraySum(intArrayOf(1, 2, 3), 3)) // 2
    println(subarraySum(intArrayOf(1), 0)) // 0
    println(subarraySum(intArrayOf(-1, -1, 1), 0)) // 1
    println(subarraySum(intArrayOf(1, -1, 0), 0)) // 3
}

// sum(i,j) = sum(0,j) - sum(0,i)
// sum(0,i) = sum(0,j) - sum(i,j)
// sum(0,i) = sum(0,j) - k

private fun subarraySum(nums: IntArray, k: Int): Int {
    val availableSumsToCount = mutableMapOf<Int, Int>()
//    availableSumsToCount[0] = 1
    var prefix = 0
    var numOfSubarraySums = 0

    for (num in nums) {
        prefix += num

        if (prefix == k) ++numOfSubarraySums
        numOfSubarraySums += availableSumsToCount[prefix - k] ?: 0

        availableSumsToCount[prefix] = (availableSumsToCount[prefix] ?: 0) + 1
    }

    return numOfSubarraySums
}

// 1234; 3214; 2314; 4132;
