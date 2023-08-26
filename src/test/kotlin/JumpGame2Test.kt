import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class JumpGame2Test {
    private lateinit var alg: JumpGame2

    @BeforeEach
    fun setUp() {
        alg = JumpGame2()
    }

    @Test
    fun `many ways test`() {
        val nums = intArrayOf(2, 3, 1, 1, 4)
        val result = alg.jump(nums)

        assertEquals(2, result)
    }

    @Test
    fun `only way test`() {
        val nums = intArrayOf(2, 3, 0, 1, 4)
        val result = alg.jump(nums)

        assertEquals(2, result)
    }

    @Test
    fun `n steps test`() {
        val nums = intArrayOf(1, 1, 1, 1, 4)
        val result = alg.jump(nums)

        assertEquals(4, result)
    }

    @Test
    fun `initial pos is finish test`() {
        val nums = intArrayOf(0)
        val result = alg.jump(nums)

        assertEquals(0, result)
    }
}