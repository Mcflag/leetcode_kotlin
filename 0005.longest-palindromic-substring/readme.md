# 5.最长回文子串

## 题目：

给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。

示例 1：

	输入: "babad"
	输出: "bab"
	注意: "aba" 也是一个有效答案。

示例 2：

	输入: "cbbd"
	输出: "bb"

## 解答一：

暴力解法，枚举每一个子字符串，判断其是否为回文串，算法复杂度为 O(n^3)。

```kotlin
fun longestPalindrome(s: String): String {
	var ans = ""
	var max = 0
	var len = s.length
	for (i in 0 until len) {
		for (j in i + 1..len) {
			var str = s.substring(i, j)
			if (isPalindromic(str) && str.length > max) {
				ans = str
				max = Math.max(max, ans.length)
			}
		}
	}
	return ans
}
```



