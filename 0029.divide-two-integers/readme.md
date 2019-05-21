# 29.两数相除

## 题目：

给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

示例 1:
	输入: dividend = 10, divisor = 3
	输出: 3

示例 2:
	输入: dividend = 7, divisor = -3
	输出: -2

说明:
* 被除数和除数均为32位有符号整数。
* 除数不为0。
* 假设我们的环境只能存储32位有符号整数，其数值范围是[−2^31, 2^31−1]。本题中，如果除法结果溢出，则返回2^31−1。

## 解答：

不能使用乘法，除法，mod运算。那么除数和被除数取绝对值，结果的符号最后再解决。

可以用被除数不停的减去除数，每次操作增加操作次数，最后操作的次数就是结果。有一些细节需要考虑到，一个是会溢出的情况
，溢出需要单独处理。

还有一个是如果一次一次的减去除数，假如除数是1，被除数是最大值，运行的时候就会超时。所以用倍数减，以及倍数增加操作次数。

这样算法复杂度是`O(log(n))`，空间复杂度是`O(1)`。

```kotlin
fun divide1(dividend: Int, divisor: Int): Int {
    if (dividend == 0) return 0
    if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE
    var positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)
    var mdividend = Math.abs(dividend.toLong())
    var mdivisor = Math.abs(divisor.toLong())
    var count = 0
    for (i in 31 downTo 0) {
        if (mdividend shr i >= mdivisor) {
            count += 1 shl i
            mdividend -= mdivisor shl i
        }
    }
    return if (positive) count else 0 - count
}
```



