package com.ccooy.testonly.leet1_20

import java.util.*

class Solution15 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var a = intArrayOf(-1, 0, 1, 2, -1, -4)
            for (i in threeSum(a)) {
                for (j in i) {
                    print(j)
                }
                println()
            }
        }

        fun threeSum(nums: IntArray): List<List<Int>> {
            nums.sort()
            var result = LinkedList<List<Int>>()
            for (i in 0 until nums.size - 2) {
                //为了保证不加入重复的 list,因为是有序的，所以如果和前一个元素相同，只需要继续后移就可以
                if (i == 0 || i > 0 && nums[i] != nums[i - 1]) {
                    //两个指针,并且头指针从i + 1开始，防止加入重复的元素
                    var lo = i + 1
                    var hi = nums.size - 1
                    val sum = 0 - nums[i]
                    while (lo < hi) {
                        if (nums[lo] + nums[hi] == sum) {
                            result.add(Arrays.asList(nums[i], nums[lo], nums[hi]))
                            //元素相同要后移，防止加入重复的 list
                            while (lo < hi && nums[lo] == nums[lo + 1]) lo++
                            while (lo < hi && nums[hi] == nums[hi - 1]) hi--
                            lo++
                            hi--
                        } else if (nums[lo] + nums[hi] < sum)
                            lo++
                        else
                            hi--
                    }
                }
            }
            return result
        }
    }
}