package com.ccooy.testonly.leet21_40

class Solution33 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var nums = intArrayOf(4, 5, 6, 7, 0, 1, 2)
            var target = 0
            println(search(nums, target))
        }

        fun search(nums: IntArray, target: Int): Int {
            if (nums.isEmpty()) return -1
            if (nums.size == 1) return if (target == nums[0]) 0 else -1
            var start = 0
            var end = nums.size - 1
            while (start <= end) {
                var mid = (start + end) / 2
                if (target == nums[mid]) return mid
                if (nums[start] <= nums[mid]) {
                    if (target >= nums[start] && target < nums[mid]) {
                        end = mid - 1
                    } else {
                        start = mid + 1
                    }
                } else {
                    if (target > nums[mid] && target <= nums[end]) {
                        start = mid + 1
                    } else {
                        end = mid - 1
                    }
                }
            }
            return -1
        }
    }
}