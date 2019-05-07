# 15.三数之和

## 题目：

给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。

注意：答案中不可以包含重复的三元组。

例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，

满足要求的三元组集合为：

	[
	  [-1, 0, 1],
	  [-1, -1, 2]
	]

## 解答：

本题主要的思路就是优化遍历查询，一般暴力破解的话，循环查三个数复杂度会到O(n^3)，不过优化之后的遍历复杂度可以降到O(n^2)。

算法要点：

1. 先将数组排序
2. 遍历数组，取到第一个数，后面两数的和就是0减去第一个数。
3. 使用两个指针，一个指向头（第一个数后面一位），一个指向尾。
4. 为了防止得到重复答案，遍历数组、头指针、尾指针移动时遇到相同的元素都需要跳过。
5. 如果头尾两数的和小于需要的和，则移动头指针，如果大于需要的值，则移动尾指针。

空间复杂度是O(N)，这个N是n个元素排列组合的个数。

```kotlin
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
```



