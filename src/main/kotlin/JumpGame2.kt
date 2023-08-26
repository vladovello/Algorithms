internal class JumpGame2 {
    fun jump(nums: IntArray): Int {
        var jumpsCount = 0
        var i = 0

        while (i < nums.size - 1) {
            if (i + nums[i] >= nums.size - 1) return jumpsCount + 1

            var longestJumpInitialPos = i
            var longestJumpFinalPos = i + nums[i]

            for (j in 1..nums[i]) {
                val jumpPos = i + j
                val jumpLongestPos = jumpPos + nums[jumpPos]

                if (jumpLongestPos > longestJumpFinalPos) {
                    longestJumpInitialPos = jumpPos
                    longestJumpFinalPos = jumpLongestPos
                }
            }

            i = longestJumpInitialPos
            ++jumpsCount
        }

        return 0
    }
}