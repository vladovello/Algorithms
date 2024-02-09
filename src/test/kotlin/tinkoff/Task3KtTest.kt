package tinkoff

import contest.tinkoff.canGetWinningSequence
import logger
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream
import javax.security.auth.callback.ConfirmationCallback.NO
import javax.security.auth.callback.ConfirmationCallback.YES

class Task3KtTest {
    val logger by logger()
    lateinit var alg: (Int, IntArray, IntArray) -> String

    @BeforeEach
    fun setUp() {
        alg = ::canGetWinningSequence
    }

    @Nested
    inner class CommonWorkflow {
        @ParameterizedTest
        @ArgumentsSource(CommonArgumentsProvider::class)
        fun `return expected result`(n: Int, a: IntArray, b: IntArray, expected: String) {
            val result = alg(n, a, b)
            logger.info("Result is '$result'")
            Assertions.assertEquals(expected, result)
        }
    }

    private class CommonArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(
                    5,
                    intArrayOf(1, 4, 2, 2, 4),
                    intArrayOf(1, 4, 4, 2, 2),
                    NO
                ),
                Arguments.of(
                    6,
                    intArrayOf(5, 1, 2, 5, 3, 5),
                    intArrayOf(5, 1, 2, 3, 5, 5),
                    YES
                ),
                Arguments.of(
                    3,
                    intArrayOf(4, 1, 2),
                    intArrayOf(1, 4, 7),
                    NO
                ),
                Arguments.of(
                    1,
                    intArrayOf(7),
                    intArrayOf(7),
                    YES
                ),
                Arguments.of(
                    7,
                    intArrayOf(4, 4, 1, 7, 5, 3, 8),
                    intArrayOf(4, 1, 4, 5, 7, 3, 8),
                    YES
                ),
                Arguments.of(
                    3,
                    intArrayOf(1, 2, 3),
                    intArrayOf(1, 4, 3),
                    NO
                ),
                Arguments.of(
                    4,
                    intArrayOf(1, 2, 3, 4),
                    intArrayOf(1, 2, 4, 4),
                    NO
                ),
                Arguments.of(
                    4,
                    intArrayOf(1, 4, 3, 3),
                    intArrayOf(1, 2, 3, 3),
                    NO
                )
            )
        }
    }
}
