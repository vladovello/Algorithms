fun main() {
    val nums = intArrayOf(6, 5, 5)
    println(MajorityElement().majorityElement(nums))
}

private class MajorityElement {
//    fun majorityElement(nums: IntArray): Int {
//        val count = hashMapOf<Int, Int>()
//        var majorityElement = nums[0]
//        count[majorityElement] = count[majorityElement]?.plus(1) ?: 1
//
//        for (i in 1 until nums.size) {
//            val currentNum = nums[i]
//
//            count[currentNum] = count[currentNum]?.plus(1) ?: 1
//
//            if (count[currentNum]!! > count[majorityElement]!!) {
//                majorityElement = currentNum
//            }
//        }
//
//        return majorityElement
//    }

    fun majorityElement(nums: IntArray): Int {
        var majorityNum = nums[0]
        var count = 1

        for (i in 1 until nums.size) {
            count += if (majorityNum == nums[i]) 1 else -1

            if (count < 0) {
                majorityNum = nums[i]
                count = 1
            }
        }

        return majorityNum
    }
}