# 33.搜索旋转排序数组

## 题目：

假设按照升序排序的数组在预先未知的某个点上进行了旋转。

( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。

搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。

你可以假设数组中不存在重复的元素。

你的算法时间复杂度必须是 O(log n) 级别。

示例 1:

	输入: nums = [4,5,6,7,0,1,2], target = 0
	输出: 4

示例 2:

	输入: nums = [4,5,6,7,0,1,2], target = 3
	输出: -1

## 解答：

主要的点在于算法时间复杂度必须是O(log n)，这直接指示可以用二分法查询。整个过程分两步，先二分查最小的点，也就是旋转点。然后再选择区域中二分查找target。空间复杂度O(1)。

```kotlin
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
```



