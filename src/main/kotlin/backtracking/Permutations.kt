package backtracking

object PermutationsSolutions {

    /**
     * Фиксируем позицию в массиве и начинаем менять местами оригинальный элемент с этой позиции со всеми остальными элементами.
     *
     * Далее рекурсивно вызываем метод для подсчета перестановок с фиксированной позицией, увеличенной на 1. Остальные действия те же самые.
     *
     * Таким образом, будем подставлять все элементы в каждую i-ю позицию
     */
    class Solution1 {

        fun permute(nums: IntArray): List<List<Int>> {
            val permutations = mutableListOf<List<Int>>()

            fun computePermutations(fixed: Int) {
                if (fixed == nums.lastIndex) {
                    permutations.add(nums.toList())
                    return
                }
                for (i in fixed..nums.lastIndex) {
                    swap(nums, fixed, i)
                    computePermutations(fixed + 1)
                    swap(nums, fixed, i)
                }
            }

            computePermutations(0)
            return permutations
        }

        private fun swap(array: IntArray, i1: Int, i2: Int) {
            val temp = array[i1]
            array[i1] = array[i2]
            array[i2] = temp
        }
    }
}
