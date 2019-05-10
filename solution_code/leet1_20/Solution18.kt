package com.ccooy.testonly.leet1_20

import java.util.*

class Solution18 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
//            var nums = intArrayOf(1, 0, -1, 0, -2, 2)
            var nums = intArrayOf(-3, -1, 0, 2, 4, 5)
            var target = 0
            for (i in fourSum(nums, target)) {
                for (j in i) {
                    print("$j ")
                }
                println()
            }
        }

        fun fourSum(nums: IntArray, target: Int): List<List<Int>> {
            nums.sort()
            var result = LinkedList<List<Int>>()
            for (i in 0 until nums.size - 3) {
                if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                    for (j in i + 1 until nums.size - 2) {
                        if (j == i + 1 || nums[j] != nums[j - 1]) {
                            var lo = j + 1
                            var hi = nums.size - 1
                            val sum = target - nums[i] - nums[j]
                            while (lo < hi) {
                                when {
                                    nums[lo] + nums[hi] == sum -> {
                                        result.add(Arrays.asList(nums[i], nums[j], nums[lo], nums[hi]))
                                        while (lo < hi && nums[lo] == nums[lo + 1]) lo++
                                        while (lo < hi && nums[hi] == nums[hi - 1]) hi--
                                        lo++
                                        hi--
                                    }
                                    nums[lo] + nums[hi] < sum -> lo++
                                    else -> hi--
                                }
                            }
                        }
                    }
                }
            }
            return result
        }
    }
}