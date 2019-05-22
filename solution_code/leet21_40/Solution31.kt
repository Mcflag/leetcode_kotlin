package com.ccooy.testonly.leet21_40

class Solution31 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val nums = intArrayOf(3,2,1)
            nextPermutation(nums)
            for(i in nums){
                println(i)
            }
        }

        fun nextPermutation(nums: IntArray): Unit {
            var i = nums.size - 2
            while (i >= 0 && nums[i + 1] <= nums[i]) {
                i--
            }
            if (i < 0) {
                reverse(nums, 0)
                return
            }
            var j = nums.size - 1
            while (j >= 0 && nums[j] <= nums[i]) {
                j--
            }
            swap(nums, i, j)
            reverse(nums, i + 1)
        }

        fun swap(nums: IntArray, i: Int, j: Int) {
            var temp = nums[j]
            nums[j] = nums[i]
            nums[i] = temp
        }

        fun reverse(nums: IntArray, start: Int) {
            var i = start
            var j = nums.size - 1
            while (i < j) {
                swap(nums, i, j)
                i++
                j--
            }
        }
    }
}