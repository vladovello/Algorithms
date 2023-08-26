import java.util.*
import kotlin.math.max
import kotlin.math.min

fun main() {
    val heights = intArrayOf(6, 4, 2, 0, 3, 2, 0, 3, 1, 4, 5, 3, 2, 7, 5, 3, 0, 1, 2, 1, 3, 4, 6, 8, 1, 3)
    println(TrappingRainWater().trap(heights))
}

class TrappingRainWater {
    class Wall(val height: Int, val index: Int, val prevOccupiedSpace: Int, var capacity: Int = 0) : Comparable<Wall> {
        override fun compareTo(other: Wall): Int {
            return if (height > other.height) 1
            else if (height < other.height) return -1
            else 0
        }
    }

    fun trap(height: IntArray): Int {
        val tank = Stack<Wall>()

        var totalCapacity = 0

        for (i in height.indices) {
            val newWall = Wall(height[i], i, if (tank.empty()) height[i] else tank.peek().prevOccupiedSpace + height[i])

            var lastRemoved: Wall? = null

            while (!tank.empty() && height[i] >= tank.peek().height) {
                lastRemoved = tank.pop()
                totalCapacity -= lastRemoved.capacity
            }

            if (lastRemoved != null) {
                val leftWall: Wall = if (tank.empty()) lastRemoved else tank.peek()
                val rightWall: Wall = newWall

                val distance = rightWall.index - leftWall.index - 1
                val occupiedSpace = rightWall.prevOccupiedSpace - rightWall.height - leftWall.prevOccupiedSpace

                val result = min(rightWall.height, leftWall.height) * distance - occupiedSpace

                if (!tank.empty()) totalCapacity -= leftWall.capacity
                leftWall.capacity = max(0, result)
                totalCapacity += leftWall.capacity
            }

            tank.add(newWall)
        }

        return totalCapacity
    }

    /* Ideal solution imo
    fun trap(height: IntArray): Int {
        var area = 0
        var l = 0
        var r = height.size-1
        var lMax = 0
        var rMax = 0
        while (l < r) {
            if (height[l] > lMax) {
                lMax = height[l]
            }
            if (height[r] > rMax) {
                rMax = height[r]
            }

            if(lMax > rMax) {
                area += maxOf(0, rMax - height[r])
                r--
            } else {
                area += maxOf(0, lMax - height[l])
                l++
            }
        }
        return area
    }
    */
}
