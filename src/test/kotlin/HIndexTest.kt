import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class HIndexTest {
    private lateinit var alg: (IntArray) -> Int

    @BeforeEach
    fun setUp() {
        alg = HIndex()::hIndex
    }

    @Test
    fun `many same values test`() {
        runTest(intArrayOf(9, 7, 7, 5, 5, 6), 5)
    }

    @Test
    fun `low values that should be excluded`() {
        runTest(intArrayOf(2, 1, 4, 3, 5, 5, 6), 4)
    }

    @Test
    fun `all values are the same`() {
        runTest(intArrayOf(5, 5, 5, 5, 5, 5, 5, 5, 5), 5)
    }

    private fun runTest(citations: IntArray, expectedHIndex: Int) {
        val result = alg(citations)
        assertEquals(expectedHIndex, result)
    }
}