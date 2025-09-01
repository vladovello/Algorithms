package common

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.scopes.FunSpecContainerScope
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.equals.shouldBeEqual
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import kotlin.system.measureNanoTime

private var anonymousClassCounter = 0

fun FunSpec.runTestsInContext(solution: Any, testCases: suspend FunSpecContainerScope.() -> Unit) {
    val contextName = solution::class.simpleName ?: ("Anonymous class $anonymousClassCounter")
    context(contextName) {
        testCases()
    }
}

fun runTestArray(expected: IntArray?, testBlock: () -> IntArray?) {
    runTest(expected, testBlock) { result, expected ->
        if (expected == null) result.shouldBeNull()
        else {
            result.shouldNotBeNull()
            result.asList() shouldContainExactly expected.asList()
        }
    }
}

fun <T : Any> runTestOverriddenEquals(expected: T?, testBlock: () -> T?) {
    runTest(expected, testBlock) { result, expected ->
        if (expected == null) result.shouldBeNull()
        else {
            result.shouldNotBeNull()
            result shouldBeEqual expected
        }
    }
}

fun <T : Any> runTest(expected: T?, testBlock: () -> T?, assertion: (result: T?, expected: T?) -> Unit) {
    val result: T?
    val elapsedTime = measureNanoTime {
        result = testBlock()
    }
    println("%,.6f ms".format(elapsedTime / 1e6))
    assertion(result, expected)
}
