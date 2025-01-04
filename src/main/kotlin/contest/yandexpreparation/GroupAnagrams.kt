package contest.yandexpreparation

fun main() {
    println("hhhhu".toSet().hashCode()) // 221
    println("tttti".toSet().hashCode()) // 221

    // ["tittt","tttit","tttti"],["hhhhu","hhhuh","hhuhh"]
    println(groupAnagrams(arrayOf("hhhhu", "tttti", "tttit", "hhhuh", "hhuhh", "tittt")))
}

// creating sets for each string
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val hashCodesToWords = mutableMapOf<Int, MutableList<String>>()

    for (str in strs) {
        val sortedStrHashCode = str.toCharArray().let { it.sort(); it.contentHashCode() }
        hashCodesToWords.getOrPut(sortedStrHashCode, ::mutableListOf).add(str)
    }

    return hashCodesToWords.values.toList()
}
