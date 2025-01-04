package contest.yandexpreparation

fun main() {
    println(findThePrefixCommonArray(intArrayOf(1, 3, 2, 4), intArrayOf(3, 1, 2, 4)).joinToString())
    println(findThePrefixCommonArray(intArrayOf(2, 3, 1), intArrayOf(3, 1, 2)).joinToString())
    println(findThePrefixCommonArray(intArrayOf(2, 4, 1, 3), intArrayOf(1, 3, 4, 2)).joinToString())
}

private fun findThePrefixCommonArray(A: IntArray, B: IntArray): IntArray {
    val commonElements = mutableSetOf<Int>()
    val prefixCommonArray = IntArray(A.size)
    var commonCount = 0

    for (i in A.indices) {
        if (A[i] == B[i]) ++commonCount
        else {
            if (commonElements.contains(A[i])) ++commonCount
            if (commonElements.contains(B[i])) ++commonCount
        }

        prefixCommonArray[i] += commonCount

        commonElements.add(A[i])
        commonElements.add(B[i])
    }

    return prefixCommonArray
}
