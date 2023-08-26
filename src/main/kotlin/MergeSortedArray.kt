fun main() {
    val nums1 = readlnOrNull()!!.split(',').map { it.toInt() }.toIntArray()
    val nums2 = readlnOrNull()!!.split(',').map { it.toInt() }.toIntArray()

    val merge = MergeSortedArray()::merge
    merge(nums1, nums1.size - nums2.size, nums2, nums2.size)

    nums1.forEach { print("$it,") }
}

private class MergeSortedArray {
    /*
    * 1.
    */

    // 1 2 2 4 6 7 7 7 15
    // 4 4 4 4 5
    // 1 2 2 4 6 7 7 7 15 4 4 4 4 5

    //    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
//        var ptr1 = 0
//        var ptr2 = 0
//
//        val result = mutableListOf<Int>()
//
//        while (ptr1 < m && ptr2 < n) {
//            if (nums1[ptr1] < nums2[ptr2]) result.add(nums1[ptr1++])
//            else result.add(nums2[ptr2++])
//        }
//
//        while (ptr1 < m) {
//            result.add(nums1[ptr1++])
//        }
//        while (ptr2 < n) {
//            result.add(nums2[ptr2++])
//        }
//
//        var i = 0
//        for (elem in result) {
//            nums1[i++] = elem
//        }
//    }
    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int) {
        var ptr1 = m - 1
        var ptr2 = n - 1
        var fillPtr = m + n - 1

        while (ptr1 >= 0 && ptr2 >= 0) {
            if (nums1[ptr1] > nums2[ptr2]) nums1[fillPtr--] = nums1[ptr1--]
            else nums1[fillPtr--] = nums2[ptr2--]
        }

        while (ptr1 >= 0) {
            nums1[fillPtr--] = nums1[ptr1--]
        }
        while (ptr2 >= 0) {
            nums1[fillPtr--] = nums2[ptr2--]
        }
    }
}