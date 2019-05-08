# 18.四数之和

## 题目：

给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。

注意：

答案中不可以包含重复的四元组。

示例：

给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。

满足要求的四元组集合为：

	[
	  [-1,  0, 0, 1],
	  [-2, -1, 1, 2],
	  [-2,  0, 0, 2]
	]

## 解答：

因为前面有一道题是三数之和，这个题目实际也可以使用同样的方式，先排序，然后逐步化为求两数之和。然后用头尾遍历的方式，得到解答。

更广泛的，对于求K数之和，可以使用递归先逐层化为求K-1数之和的问题，最后成为求两数之和的问题，得到解答。

这种问题的时间复杂度为`O(n^(k-1))`，空间复杂度为`O(N)`，N是从总共n个元素取k个元素排列组合的次数。

```kotlin
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
```



