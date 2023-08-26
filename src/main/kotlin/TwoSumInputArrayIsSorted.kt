class TwoSumInputArrayIsSorted {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var left = 0
        var right = numbers.lastIndex

        while (left < right) {
            val sum = numbers[left] + numbers[right]

            when {
                sum == target -> return intArrayOf(left + 1, right + 1)
                sum < target -> ++left
                else -> --right
            }
        }

        return intArrayOf(-1, -1)
    }

    /* Slow one
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        for (i in 0 until numbers.lastIndex) {
            val desiredNumber = target - numbers[i]
            var left = i + 1
            var right = numbers.lastIndex

            while (left <= right) {
                val mid = (left + right) / 2

                if (numbers[mid] == desiredNumber) return intArrayOf(i + 1, mid + 1)
                else if (numbers[mid] < desiredNumber) left = mid + 1
                else right = mid - 1
            }
        }

        return intArrayOf(-1, -1)
    }
    */
}