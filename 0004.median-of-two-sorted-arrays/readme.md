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

这个解法的主要思路是（先只考虑普通情况）：

A数组有m个数，B数组有n个数，在i位置分割A数组，在j位置分割B数组，所以有

	      left_part          |         right_part
	A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
	B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]

满足len(left_part)等于len(right_part)或者等于len(right_part)+1，如果i在0到m的范围内，根据这个公式能够将j用m，n，i进行表示。

另一个需要满足的条件是A[i-1]<=B[j]，以及B[j-1]<=A[i]。

通过二分法在0到m中查找满足条件的i。为什么可以使用二分法查找？

因为如果i变大，j就变小，A[i]就变大，B[j]就会变小；如果i变小，j就变大，A[i]就变小，B[j]就会变大。这样在进行比较的时候如果A[i-1]>B[j]，就可以减小i，如果B[j-1]>A[i]，就可以增大i。

通过判断两个数组分割处之间的联系，以及使用二分查找，可以将算法复杂度降低到 O(log(min(m,n)))，同时空间复杂度也只有 O(1)。

```kotlin
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
	val anums = if (nums1.size > nums2.size) nums2 else nums1
	val bnums = if (nums1.size > nums2.size) nums1 else nums2
	val m = anums.size
	val n = bnums.size
	var iMin = 0
	var iMax = m
	val halfLen = (m + n + 1) / 2
	while (iMin <= iMax) {
		val i = (iMin + iMax) / 2
		val j = halfLen - i
		if (i < iMax && bnums[j - 1] > anums[i]) {
			iMin = i + 1
		} else if (i > iMin && anums[i - 1] > bnums[j]) {
			iMax = i - 1
		} else {
			val maxLeft = when {
				i == 0 -> bnums[j - 1]
				j == 0 -> anums[i - 1]
				else -> Math.max(anums[i - 1], bnums[j - 1])
			}
			if ((m + n) % 2 == 1) {
				return maxLeft.toDouble()
			}

			val minRight = when {
				i == m -> bnums[j]
				j == n -> anums[i]
				else -> Math.min(bnums[j], anums[i])
			}
			return (maxLeft + minRight) / 2.0
		}

	}
	return 0.0
}
```

## 解法2

举个例子，比如a=[1 2 3 4 6 9]和b=[1 1 5 6 9 10 11]，总共13个元素，需要找到中间的第7个元素。int(7/2)=3，a[3]<b[3]，所以可以不必考虑a[0]、a[1]、a[2]这三个元素，因为他们不可能成为第七个元素。 然后在剩下的数中找第4个元素（目标7，排除的元素3，现在的目标是4）。

这样的算法用到了递归以及二分法，所以算法复杂度满足题目要求的 O(log(m + n))。

```kotlin
fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
	val len1 = nums1.size
	val len2 = nums2.size
	val sumLen = len1 + len2
	return if (sumLen % 2 != 0) {
		findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2 + 1).toDouble()
	} else {
		(findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2) +
			findKthSmallest(nums1, len1, 0, nums2, len2, 0, sumLen / 2 + 1)) / 2.0
	}
}
fun findKthSmallest(a: IntArray, m: Int, begin1: Int, b: IntArray, n: Int, begin2: Int, k: Int): Int {
	if (m > n)
		return findKthSmallest(b, n, begin2, a, m, begin1, k)
	if (m == 0)
		return b[begin2 + k - 1]
	if (k == 1)
		return Math.min(a[begin1], b[begin2])
	val partA = Math.min(k / 2, m)
	val partB = k - partA
	return if (a[begin1 + partA - 1] == b[begin2 + partB - 1])
		a[begin1 + partA - 1]
	else if (a[begin1 + partA - 1] > b[begin2 + partB - 1])
		findKthSmallest(a, m, begin1, b, n - partB, begin2 + partB, k - partB)
	else
		findKthSmallest(a, m - partA, begin1 + partA, b, n, begin2, k - partA)
}
```

## 解法3

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

