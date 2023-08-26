import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class GasStationTest {
    private val logger by logger()
    private lateinit var alg: (IntArray, IntArray) -> Int

    @BeforeEach
    fun setUp() {
        alg = GasStation()::canCompleteCircuit
    }

    @Test
    fun `increasing gas and different cost`() {
        runTest(intArrayOf(1, 2, 3, 4, 5), intArrayOf(3, 4, 5, 1, 2), 3)
    }

    @Test
    fun `failing to find path`() {
        runTest(intArrayOf(2, 3, 4), intArrayOf(3, 4, 3), -1)
    }

    @Test
    fun `failing to find path 2`() {
        runTest(intArrayOf(4, 5, 2, 6, 5, 3), intArrayOf(3, 2, 7, 3, 2, 9), -1)
    }

    private fun runTest(gas: IntArray, cost: IntArray, expected: Int) {
        val result = alg(gas, cost)
        logger.info("Result is: '$result'")
        assertEquals(expected, result)
    }
}