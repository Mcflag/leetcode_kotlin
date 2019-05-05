# 14.最长公共前缀

## 题目：

编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

	示例 1:
	输入: ["flower","flow","flight"]
	输出: "fl"

	示例 2:
	输入: ["dog","racecar","car"]
	输出: ""
	解释: 输入不存在公共前缀。

说明:所有输入只包含小写字母 a-z 。

## 解答一：

二分法查找。因为是公共前缀，先找出数组中最短的字符串 L。然后用这个最短的字符串 L 去和后面的所有字符串循环比对，同时在这里用了二分法，一次排除一般的情况，否则就需要将L从最长到最短的子串依次查找，这样做没有二分法的效率高。

最坏的情况下，有n个长度为m的字符串，算法复杂度`O(m*n*log(n))`，空间复杂度为`O(1)`。

```kotlin
fun longestCommonPrefix(strs: Array<String>): String {
	if (strs.isEmpty()) return ""
	if (strs.size == 1) return strs.first()
	val minLen = strs.map { it.length }.min()!!

	var low = 1
	var high = minLen

	while (low <= high) {
		val middle = (low + high) / 2

		if (isCommonPrefix(strs, middle)) {
			low = middle + 1
		} else {
			high = middle - 1
		}
	}
	return strs.first().substring(0, (low + high) / 2)
}

fun isCommonPrefix(strs: Array<String>, prefixLen: Int): Boolean {
	if (prefixLen == 0) return false
	val prefix = strs.first().substring(0, prefixLen)
	for (index in 1..(strs.size - 1)) {
		if (!strs[index].startsWith(prefix)) {
			return false
		}
	}
	return true
}
```

## 解答二：

kotlin极简的写法，灵活使用reduce()方法。该方法实际就是水平比较，算法复杂度是`O(m*n)`，空间复杂度是`O(1)`

```kotlin
fun longestCommonPrefix(strs: Array<String>): String {
	return if (strs.isEmpty()) "" else strs.reduce { a, b -> a.commonPrefixWith(b) }
}
```

## 解答三：

垂直比较，所有字符串垂直排列，然后一列一列比较字符，直到某一个字符串到达结尾或者该列字符不完全相同。

```kotlin
fun longestCommonPrefix1(strs: Array<String>): String {
	if (strs.isEmpty()) return ""
	for (i in 0 until strs[0].length) {
		val c = strs[0][i]
		for (j in 1 until strs.size) {
			if (i == strs[j].length || strs[j][i] != c)
				return strs[0].substring(0, i)
		}
	}
	return strs[0]
}
```