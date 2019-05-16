package com.ccooy.testonly.leet21_40

class Solution26 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var a = intArrayOf(0, 0, 1, 1, 1, 2, 2, 3, 3, 4)
            println(removeDuplicates(a))
        }

        fun removeDuplicates(nums: IntArray): Int {
            if (nums.isEmpty()) return 0
            var i = 0
            for (j in 1 until nums.size) {
                if (nums[j] != nums[i]) {
                    i++
                    nums[i] = nums[j]
                }
            }
            return i + 1
        }
    }
}