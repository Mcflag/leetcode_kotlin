package com.ccooy.testonly.leet21_40

class Solution27 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var r = intArrayOf(0, 1, 2, 2, 3, 0, 4, 2)
            var target = 2
            println(removeElement(r, target))
        }

        fun removeElement1(nums: IntArray, `val`: Int): Int {
            if (nums.isEmpty()) return 0
            var i = -1
            for (j in 0 until nums.size) {
                if (nums[j] != `val`) {
                    i++
                    nums[i] = nums[j]
                }
            }
            return i + 1
        }

        fun removeElement(nums: IntArray, `val`: Int): Int {
            var i = 0
            var newSize = nums.size
            while (i < newSize) {
                if (nums[i] == `val`) {
                    nums[i] = nums[newSize - 1]
                    newSize--
                } else {
                    i++
                }
            }
            return newSize
        }
    }
}