import kotlin.math.abs

fun main() {
    val heights = intArrayOf(1, 0, 3, 0, 0, 0, 2)
    println(TrappingRainWater_OOPTry().trap(heights))
}

internal class TrappingRainWater_OOPTry {
    private class Tank {
        class Section {
            class Wall(val height: Int, val index: Int) : Comparable<Wall> {
                override fun compareTo(other: Wall): Int {
                    return if (height > other.height) 1
                    else if (height < other.height) return -1
                    else 0
                }
            }

            private val valuableWalls = mutableListOf<Wall>()
            private var occupiedSpace = 0

            var capacity = 0
                private set
            var isLocked = false
                private set

            /**
             * @return Pushed wall
             */
            private fun push(item: Wall): Wall {
                while (valuableWalls.size > 1 && item >= valuableWalls.last()) {
                    valuableWalls.removeLast()
                }

                if (valuableWalls.size == 1 && item >= valuableWalls.first()) {
                    valuableWalls.add(0, item)
                    isLocked = true
                }
                else valuableWalls.add(item)

                occupiedSpace += item.height

                return item
            }

            /**
             * @return Tank capacity
             */
            private fun calculateCapacity(): Int {
                if (valuableWalls.size <= 1) return 0

                val first = valuableWalls.first()
                val last = valuableWalls.last()
                val occupiedSpaceBetweenWalls = occupiedSpace - first.height - last.height

                val result = last.height * (abs(last.index - first.index) - 1) - occupiedSpaceBetweenWalls

                return if (result >= 0) result else 0
            }

            fun addWall(wall: Wall): Boolean {
                if (isLocked) return false

//                val initialSize = valuableWalls.size

                push(wall)
                capacity += calculateCapacity()

//                if (valuableWalls.size == 1 && valuableWalls.size < initialSize) isLocked = true

                return true
            }
        }

        private val sections = mutableListOf<Section>()

        var tankCapacity = 0
            private set

        fun addWall(height: Int, index: Int) {
            if (sections.isEmpty()) sections.add(Section())

            val lastSection = sections.last()

            lastSection.addWall(Section.Wall(height, index))

            if (lastSection.isLocked) {
                tankCapacity += lastSection.capacity
                sections.add(Section())
            }
        }
    }

    fun trap(height: IntArray): Int {
        val tank = Tank()

        for (i in height.indices) {
            if (height[i] != 0) tank.addWall(height[i], i)
        }

        return tank.tankCapacity
    }
}