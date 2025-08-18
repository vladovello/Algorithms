package binarysearch

class SearchInRotatedSortedArray {
    // [4,-8,-7,-6,-5,-4,-3,-2,-1,0,1,2]
    // [4,5,-2,-1,0,1,2]
    // [1,2,3,4,5,6]
    // [6,7,1,2,3,4]
    // [2,3,4,6,7,1]
    // [2,7,8,9,10,1]
    // [4,5,6,7,1,2]

    // t > r && t > m - right
    // t < r && t > m - right
    // t > r && t < m - left
    // t < r && t < m - left
    fun search(nums: IntArray, target: Int): Int {
        var left = 0
        var right = nums.lastIndex

        while (left <= right) {
            val mid = left + (right - left) / 2

            if (nums[mid] == target) {
                return mid
            }

            if (nums[left] <= nums[mid]) {
                if (nums[left] <= target && nums[mid] > target) {
                    right = mid - 1
                } else {
                    left = mid + 1
                }
            } else {
                if (nums[right] >= target && nums[mid] < target) {
                    left = mid + 1
                } else {
                    right = mid - 1
                }
            }
        }

        return -1
    }
}
