# 9.回文数

## 题目：

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

	示例 1:
	输入: 121
	输出: true

	示例 2:
	输入: -121
	输出: false
	解释: 从左向右读，为-121。从右向左读，为121-。因此它不是一个回文数。

	示例 3:
	输入: 10
	输出: false
	解释: 从右向左读，为01。因此它不是一个回文数。

进阶:

你能不将整数转为字符串来解决这个问题吗？

## 解答一：

Int转成字符串，然后与倒序后的字符串比较看是否相等，简单粗暴。

```kotlin
fun isPalindrome(x: Int): Boolean {
	return x.toString() == x.toString().reversed()
}
```

## 解答二：

参考第七题的Int倒转，现在只需要考虑是否有Int溢出的问题。Int最大值为2147483647，如果倒转后的Int会溢出，根据回文的条件倒转后和原数是一样的，那么原数也必定会溢出。所以不可能存在回文的Int倒转后会溢出，可以不用考虑溢出这个问题。

这个算法中数字有多少位就循环多少次，所以算法复杂度是 O(n)，空间复杂度是 O(1)。

```kotlin
fun isPalindrome1(x: Int): Boolean {
	if (x < 0) return false
	if ((x % 10) == 0 && (x != 0)) return false
	var temp = x
	var reverse = 0
	while (temp != 0) {
		val pop = temp % 10
		temp /= 10
		reverse = reverse * 10 + pop
	}
	return x == reverse
}
```


