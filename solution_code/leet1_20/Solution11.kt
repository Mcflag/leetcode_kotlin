package com.ccooy.testonly.leet1_20

class Solution11 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            // [1,8,6,2,5,4,8,3,7]
            var a = intArrayOf(1, 1, 2, 2, 99, 3, 3, 4, 4) // ans:49
            println(maxArea(a))
        }

        fun maxArea(height: IntArray): Int {
            var start = 0
            var end = height.size - 1
            var max = 0
            while (start < end) {
                max = Math.max(max, Math.min(height[start], height[end]) * (end - start))
                if (height[start] < height[end]) {
                    start++
                } else {
                    end--
                }

            }
            return max
        }
    }
}