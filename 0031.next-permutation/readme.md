# 31.下一个排列

## 题目：

实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。

如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。

必须原地修改，只允许使用额外常数空间。

以下是一些例子，输入位于左侧列，其相应输出位于右侧列。

	1,2,3 → 1,3,2
	3,2,1 → 1,2,3
	1,1,5 → 1,5,1

## 解答：

从最右边开始判断。

1. 从右向左找到第一个数字不再递增的位置i。 比如“[1,5,8,4,7,6,5,3,1]”，从右开始第一个不再递增的数是4，i=3。
2. 再在右边找到刚好大于这个数字的位置j。刚好大于4的数是5，j=6。
3. 交换两个位置的值。原数组变为“[1,5,8,5,7,6,4,3,1]”。
4. 右边的所有数字升序排列。这里不用排序算法，只需要按轴对称交换j两端的数即可。直接交换“7”和“1”，“6”和“3”，得到最后的结果“[1,5,8,5,1,3,4,6,7]”。

这个算法的时间复杂度是O(n)，空间复杂度O(1)。

```kotlin
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
```

```kotlin
fun swap(nums: IntArray, i: Int, j: Int) {
    var temp = nums[j]
    nums[j] = nums[i]
    nums[i] = temp
}
```

```kotlin
fun reverse(nums: IntArray, start: Int) {
    var i = start
    var j = nums.size - 1
    while (i < j) {
        swap(nums, i, j)
        i++
        j--
    }
}
```



