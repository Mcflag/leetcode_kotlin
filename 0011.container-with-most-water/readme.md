# 11.盛最多水的容器

## 题目：

给定 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai)。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。

说明：你不能倾斜容器，且 n 的值至少为 2。

![container-with-most-water](https://raw.githubusercontent.com/Mcflag/leetcode_kotlin/master/0011.container-with-most-water/pic/question_11.jpg)

图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。

示例:

输入: [1,8,6,2,5,4,8,3,7]
输出: 49

## 解答：

初始考虑暴力求解，两层循环的求解即可，复杂度O(n^2)。但是发现可以优化，容纳水的最大值由两端的距离，以及两端最小的数决定。所以从两端开始求解，每次两端中小的那个值的下标，直到两端点相同为止。这样复杂度只有O(n)，空间复杂度O(1)。

```kotlin
fun maxArea(height: IntArray): Int {
	var start = 0
	var end = height.size - 1
	var max = 0
	while (start < end) {
		max = Math.max(max, Math.min(height[start], height[end]) * (end - start))
		if (height[start] < height[end]) {
			start++
		} else {
			end--
		}
	}
	return max
}
```



