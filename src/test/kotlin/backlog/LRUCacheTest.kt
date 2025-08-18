package backlog

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.equals.shouldBeEqual
import kotlin.reflect.full.primaryConstructor

class LRUCacheTest : FunSpec({
    context("LRUCacheTest.Solution1") {
        test("Input: [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]") {
            val result = runCacheOperationsAndWriteResult<LRUCacheSolutions.Solution1>(
                listOf("LRUCache", "put", "put", "put", "put", "get", "get"),
                "[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]"
            )

            result shouldBeEqual listOf(null, null, null, null, null, -1, 3)
        }
    }

    context("LRUCacheTest.Solution2") {
        test("Input: [[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]") {
            val result = runCacheOperationsAndWriteResult<LRUCacheSolutions.Solution2>(
                listOf("LRUCache", "put", "put", "put", "put", "get", "get"),
                "[[2],[2,1],[1,1],[2,3],[4,1],[1],[2]]"
            )

            result shouldBeEqual listOf(null, null, null, null, null, -1, 3)
        }

        test("Input: [[1],[2,1],[2],[3,2],[2],[3]]") {
            val result = runCacheOperationsAndWriteResult<LRUCacheSolutions.Solution2>(
                listOf("LRUCache", "put", "get", "put", "get", "get"),
                "[[1],[2,1],[2],[3,2],[2],[3]]"
            )

            result shouldBeEqual listOf(null, null, 1, null, -1, 2)
        }

        test("Input: [[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]") {
            val result = runCacheOperationsAndWriteResult<LRUCacheSolutions.Solution2>(
                listOf("LRUCache","put","put","get","put","get","put","get","get","get"),
                "[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]"
            )

            result shouldBeEqual listOf(null,null,null,1,null,-1,null,-1,3,4)
        }
    }
})

private const val PUT = "put"
private const val GET = "get"

private inline fun <reified C : LRUCache> runCacheOperationsAndWriteResult(operations: List<String>, input: String): List<Int?> {
    val arrayInput = parseNestedIntArray(input)

    if (operations.size != arrayInput.size) {
        throw IllegalArgumentException("operations and input doesn't have the same size")
    }

    val result = MutableList<Int?>(operations.size) { null }

    val cache = C::class.primaryConstructor?.call(arrayInput[0][0])
        ?: throw IllegalArgumentException("LRUCache constructor must contain single argument primary constructor with capacity")
    for (i in 1..operations.lastIndex) {
        when (operations[i]) {
            GET -> {
                result[i] = cache.get(arrayInput[i][0])
            }

            PUT -> {
                cache.put(arrayInput[i][0], arrayInput[i][1])
            }
        }
    }

    return result
}

private fun parseNestedIntArray(input: String): List<IntArray> {
    val regex = """\[(?:\s*-?\d+\s*(?:,\s*-?\d+\s*)*)?]""".toRegex()
    return regex.findAll(input)
        .map { match ->
            val inner = match.value
                .removeSurrounding("[", "]")
                .split(",")
                .filter { it.isNotBlank() }
                .map { it.trim().toInt() }
                .toIntArray()
            inner
        }
        .toList()
}
