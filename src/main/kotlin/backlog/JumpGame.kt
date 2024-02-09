fun main() {
    val nums = intArrayOf(3, 2, 1, 0, 4)
    println(JumpGame().canJump(nums))
    println(JumpGame().canJump2(nums))
}

private class JumpGame {
    fun canJump(nums: IntArray): Boolean {
        var maxJumpLen = 0

        for (i in 0 until nums.size - 1) {
            --maxJumpLen
            if (nums[i] == 0 && maxJumpLen <= 0) return false
            if (nums[i] > maxJumpLen) maxJumpLen = nums[i]
        }

        return true
    }

    fun canJump2(nums: IntArray): Boolean {
        var i = nums.size - 2
        var goal = nums.size - 1

        while (i >= 0) {
            if (nums[i] >= goal - i) goal = i
            --i
        }

        return goal == 0
    }
}