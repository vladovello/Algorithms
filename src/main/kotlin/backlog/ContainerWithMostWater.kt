import kotlin.math.min

fun main() {
    println(ContainerWithMostWater().maxArea(intArrayOf(1, 8, 6, 2, 5, 4, 8, 3, 7)))
}

class ContainerWithMostWater {
    fun maxArea(height: IntArray): Int {
        var left = 0
        var right = height.lastIndex
        var maxCapacity = calculateCapacity(height[left], left, height[right], right)

        while (left < right) {
            if (height[left] < height[right]) ++left
            else --right

            val newCapacity = calculateCapacity(height[left], left, height[right], right)

            if (newCapacity > maxCapacity) maxCapacity = newCapacity
        }

        return maxCapacity
    }

    private fun calculateCapacity(leftWall: Int, leftWallIndex: Int, rightWall: Int, rightWallIndex: Int): Int {
        return min(leftWall, rightWall) * (rightWallIndex - leftWallIndex)
    }
}