import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.extension.ExtensionContext
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.ArgumentsProvider
import org.junit.jupiter.params.provider.ArgumentsSource
import java.util.stream.Stream

class TextJustificationTest {
    val logger by logger()
    lateinit var alg: (Array<String>, Int) -> List<String>

    @BeforeEach
    fun setUp() {
        alg = TextJustification()::fullJustify
    }

    @Nested
    inner class CommonWorkflow {
        @ParameterizedTest
        @ArgumentsSource(CommonArgumentsProvider::class)
        fun `return expected result`(words: Array<String>, maxWidth: Int, expected: List<String>) {
            val result = alg(words, maxWidth)
            logger.info("Result is '$result'")
            assertEquals(expected, result)
        }
    }

    private class CommonArgumentsProvider : ArgumentsProvider {
        override fun provideArguments(context: ExtensionContext?): Stream<out Arguments> {
            return Stream.of(
                Arguments.of(
                    arrayOf("This", "is", "an", "example", "of", "text", "justification."),
                    16,
                    listOf(
                        "This    is    an",
                        "example  of text",
                        "justification.  "
                    )
                ),

                Arguments.of(
                    arrayOf("What", "must", "be", "acknowledgment", "shall", "be"),
                    16,
                    listOf(
                        "What   must   be",
                        "acknowledgment  ",
                        "shall be        "
                    )
                ),

                Arguments.of(
                    arrayOf(
                        "Science",
                        "is",
                        "what",
                        "we",
                        "understand",
                        "well",
                        "enough",
                        "to",
                        "explain",
                        "to",
                        "a",
                        "computer.",
                        "Art",
                        "is",
                        "everything",
                        "else",
                        "we",
                        "do"
                    ),
                    20,
                    listOf(
                        "Science  is  what we",
                        "understand      well",
                        "enough to explain to",
                        "a  computer.  Art is",
                        "everything  else  we",
                        "do                  "
                    )
                ),
            )
        }
    }
}