# 7.整数反转

## 题目：

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

	示例 1:
	输入: 123
	输出: 321

	示例 2:
	输入: -123
	输出: -321

	示例 3:
	输入: 120
	输出: 21

注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为`[−2^31,  2^31 − 1]`。请根据这个假设，如果反转后整数溢出那么就返回 0。

## 解答：

本题思路就是把数不停除10取余，然后再result中乘10加起来。主要考的应该是对溢出的处理。偷懒的办法是用Long来存result，最后用result与Int的最大值和最小值比较，溢出就返回0。

另外就是result的每一位与Int的最大值和最小值的每一位进行比较，如果判断溢出就返回0。

```kotlin
fun reverse(x: Int): Int {
	var temp = x
	var result: Long = 0
	while (temp != 0) {
		var pop = temp % 10
		temp /= 10
		result = result * 10 + pop
	}
	if (result > Int.MAX_VALUE || result < Int.MIN_VALUE) return 0
	return result.toInt()
}
```



