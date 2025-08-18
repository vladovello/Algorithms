package contest.yandexpreparation

import java.util.Stack

fun main() {
    println(trapMy(intArrayOf(0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1))) // 6
    println(trapMy(intArrayOf(4, 2, 0, 3, 2, 5))) // 9
}

// My solution (slightly slower than above)
fun trapMy(height: IntArray): Int {
    var left = 0
    var leftMaxHeight = 0
    var right = height.lastIndex
    var rightMaxHeight = 0
    var capacity = 0

    while (left < right) {
        if (height[left] > leftMaxHeight) leftMaxHeight = height[left]
        if (height[right] > rightMaxHeight) rightMaxHeight = height[right]

        capacity += if (leftMaxHeight <= rightMaxHeight) leftMaxHeight - height[left++]
        else rightMaxHeight - height[right--]
    }

    return capacity
}

// Top solution (by time)
fun trapTop(height: IntArray): Int {
    var left = 0
    var leftMaxHeight = 0
    var right = height.lastIndex
    var rightMaxHeight = 0
    var capacity = 0

    while (left < right) {
        if (height[left] <= height[right]) {
            if (height[left] > leftMaxHeight) leftMaxHeight = height[left]
            else capacity += leftMaxHeight - height[left++]
        } else {
            if (height[right] > rightMaxHeight) rightMaxHeight = height[right]
            else capacity += rightMaxHeight - height[right--]
        }
    }

    return capacity
}

// Not working
fun trapShit2(height: IntArray): Int {
    var left = 0
    var leftMax = 1
    var right = height.lastIndex
    var rightMax = height.lastIndex - 1

    val leftStack = Stack<Int>()
    val rightStack = Stack<Int>()
    var capacity = 0

    // while (leftMax < height.size && rightMax >= 0) {
    while (left < right) {
        // if () {
        // }
        if (height[leftMax] < height[left]) leftStack.push(height[leftMax++])
        else {
            while (leftStack.isNotEmpty()) capacity += height[left] - leftStack.pop()
            left = leftMax
            ++leftMax
        }

        if (height[rightMax] < height[right]) rightStack.push(height[rightMax--])
        else {
            while (rightStack.isNotEmpty()) capacity += height[right] - rightStack.pop()
            right = rightMax
            --rightMax
        }
    }

    return capacity
}

// Not working
fun trapShit(height: IntArray): Int {
    var left = 0
    var right = 1
    val stack = Stack<Int>()
    var capacity = 0

    while (right < height.size) {
        if (height[right] < height[left]) stack.push(height[right++])
        else {
            while (stack.isNotEmpty()) capacity += height[left] - stack.pop()
            left = right
            ++right
        }
    }

    while (stack.isNotEmpty()) capacity += stack.pop()

    return capacity
}
