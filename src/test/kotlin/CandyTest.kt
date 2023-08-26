import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class CandyTest {
    val logger by logger()
    lateinit var alg: (IntArray) -> Int

    @BeforeEach
    fun setUp() {
        alg = Candy()::candy
    }

    @Nested
    inner class CommonWorkflow {
        @ParameterizedTest
        @ArgumentsSource(CommonArgumentsProvider::class)
        fun `return expected result`(ratingResult: Pair<IntArray, Int>) {
            val result = alg(ratingResult.first)
            logger.info("Result is '$result'")
            assertEquals(ratingResult.second, result)
        }
    }

    private class CommonArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(Pair(intArrayOf(2, 3, 5, 4, 3, 2, 1), 18)),
                Arguments.of(Pair(intArrayOf(1, 3, 2, 2, 1), 7)),
                Arguments.of(Pair(intArrayOf(1, 2, 87, 87, 87, 2, 1), 13)),
                Arguments.of(Pair(intArrayOf(1, 2, 3, 3, 2, 1), 12)),
                Arguments.of(Pair(intArrayOf(1, 2, 3, 4, 5), 15)),
            )
        }
    }
}