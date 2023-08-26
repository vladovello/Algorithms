package hashmap

class TwoSum {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val indices = IntArray(2)
        val reminders = hashMapOf<Int, Int>()

        for (i in nums.indices) {
            val desiredNumber = target - nums[i]

            if (reminders.containsKey(desiredNumber)) {
                indices[0] = reminders[desiredNumber]!!
                indices[1] = i
                break
            }

            reminders[nums[i]] = i
        }

        return indices
    }
}