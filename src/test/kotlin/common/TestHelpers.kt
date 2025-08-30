package common

import io.kotest.core.spec.style.FunSpec
import io.kotest.core.spec.style.scopes.FunSpecContainerScope

private var anonymousClassCounter = 0

fun FunSpec.runTestsInContext(solution: Any, testCases: suspend FunSpecContainerScope.() -> Unit) {
    val contextName = solution::class.simpleName ?: ("Anonymous class $anonymousClassCounter")
    context(contextName) {
        testCases()
    }
}
