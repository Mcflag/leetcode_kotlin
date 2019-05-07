# 16.最接近的三数之和

## 题目：

给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).

## 解答：

与15题一样，先排序，再从头和尾两端开始查。时间复杂度O(n^2)，空间复杂度O(1)。

```kotlin
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
```



