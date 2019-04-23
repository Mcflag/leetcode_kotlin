# 4.寻找两个有序数组的中位数

## 题目：

给定两个大小为 *m* 和 *n* 的有序数组 *nums1* 和 *nums2*。

请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。

你可以假设 *nums1* 和 *nums2* 不会同时为空。

示例 1:

	nums1 = [1, 3]
	nums2 = [2]
	则中位数是 2.0

示例 2:

	nums1 = [1, 2]
	nums2 = [3, 4]
	则中位数是 (2 + 3)/2 = 2.5

## 解答：

注意题目对算法的时间复杂度有要求，下面这个算法时间复杂度可以降低到 O(log(min(m,n)))。

```kotlin
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
	val A = if (nums1.size > nums2.size) nums2 else nums1
	val B = if (nums1.size > nums2.size) nums1 else nums2
	val m = A.size
	val n = B.size
	var left = 0
	// +1是为了保证结果在left~right中
	var right = m + 1
	while (left < right) {
		// middle和divideB把两个数组平分成两份，如果为奇数，右边一堆比左边一堆多一个数
		val middle = (left + right) / 2
		val divideB = (m + n) / 2 - middle
		when {
			// middle 大了
			middle > 0 && divideB < n && A[middle - 1] > B[divideB] -> right = middle
			// middle 小了
			divideB > 0 && middle < m && B[divideB - 1] > A[middle] -> left = middle + 1
			// middle 合适
			else -> {
				val maxLeft = Math.max(
					if (middle > 0) A[middle - 1] else Int.MIN_VALUE,
					if (divideB > 0) B[divideB - 1] else Int.MIN_VALUE
				)
				val minRight = Math.min(
					if (middle < m) A[middle] else Int.MAX_VALUE,
					if (divideB < n) B[divideB] else Int.MAX_VALUE
				)
				return if ((m + n) % 2 == 1) minRight.toDouble() else (maxLeft + minRight) / 2.0
			}
		}
	}
	return 0.0	
}
```

## 解法2

将两个数组合并排序，取中位数。

因为题目给的是有序数组，这里可以用一个优化的组合排序方法，两个数组均从最小一端开始取值比较，较小的值存入合并后的数组，然后继续比较后一个值与另一个数组数值的大小。只需要一遍遍历即可得到合并后的排序数组，计算中位数只需要知道中间一个数或两个数即可。

这样的算法时间复杂度应该是 O(m+n)

```kotlin
fun findMedianSortedArrays1(nums1: IntArray, nums2: IntArray): Double {
	var n1 = nums1.size
	var n2 = nums2.size
	var i = 0
	var j = 0
	var arr = IntArray(n1 + n2)
	var idx = 0
	while (i < n1 && j < n2) {
		if (nums1[i] <= nums2[j]) {
			arr[idx++] = nums1[i++]
		} else {
			arr[idx++] = nums2[j++]
		}
	}
	while (i < n1) arr[idx++] = nums1[i++]
	while (j < n2) arr[idx++] = nums2[j++]
	var mid = arr.size / 2
	return if (arr.size % 2 == 0) (arr[mid - 1] + arr[mid]) / 2.0 else arr[mid].toDouble()
}
```

