package com.ccooy.testonly.leet1_20

class Solution01 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val target = 9
            val arr = intArrayOf(1, 3, 4, 7, 3, 19, -10)
            twoSum(arr, target).map {
                println(it)
            }
        }

        fun twoSum(nums: IntArray, target: Int): IntArray {
            val map = HashMap<Int, Int>()
            for ((i, v) in nums.withIndex()) {
                if (map.contains(v)) {
                    return intArrayOf(map[v] ?: -1, i)
                }
                map[target - v] = i
            }
            return intArrayOf()
        }
    }

}