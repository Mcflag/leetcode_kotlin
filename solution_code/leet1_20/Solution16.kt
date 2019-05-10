package com.ccooy.testonly.leet1_20

class Solution16 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(-1, 2, 1, -4)
            val target = 1
            println(threeSumClosest(nums, target))
        }

        fun threeSumClosest(nums: IntArray, target: Int): Int {
            nums.sort()
            var sub = Int.MAX_VALUE
            var sum = 0
            for (i in 0 until nums.size) {
                var lo = i + 1
                var hi = nums.size - 1
                while (lo < hi) {
                    if (Math.abs(nums[lo] + nums[hi] + nums[i] - target) < sub) {
                        sum = nums[lo] + nums[hi] + nums[i]
                        sub = Math.abs(sum - target)
                    }
                    if (nums[lo] + nums[hi] + nums[i] > target) {
                        hi--
                    } else {
                        lo++
                    }
                }
            }
            return sum
        }
    }
}