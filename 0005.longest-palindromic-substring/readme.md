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

## 解答二：

将原字符串反向，取原字符串和反向字符串的最长公共子串。总共三个注意的要点：

* 核心思想是，两层循环逐个比较是否相等，用一个二维数组array存储比较结果，如果`a[i]=b[j]`，`array[i][j]=array[i-1][j-1]+1`。
* 要注意的是每次得到的结果必须验证，字符串在原字符串的位置必须一样才行。比如考虑“abc53cba”，与反向之后的串能得到的“abc”和“cba”两个公共子串，但它们不是回文。原因就是他们跟不同位置的字符串相同了。所以在每次结果时都需要验证一下。
* 可以优化存储结果的二维数组为一维数组，因为每次循环时只用到了前一轮的结果。只不过因为`array[i][j]`和`array[i-1][j-1]`有关，所以内层循环时反向循环即可。

算法复杂度应该是 O(n^2)

```kotlin
fun longestPalindrome2(s: String): String {
	if (s.equals("")) return ""
	var reverse = s.reversed()
	var len = s.length
	var arr = IntArray(len)
	var maxLen = 0
	var maxEnd = 0
	for (i in 0 until len) {
		for (j in (len - 1) downTo 0) {
			if (s[i] == reverse[j]) {
				if (i == 0 || j == 0) {
					arr[j] = 1
				} else {
					arr[j] = arr[j - 1] + 1
				}
			} else {
				arr[j] = 0
			}
			if (arr[j] > maxLen) {
				var before = len - 1 - j
				if (before + arr[j] - 1 == i) {
					maxLen = arr[j]
					maxEnd = i
				}
			}
		}
	}
	return s.substring(maxEnd - maxLen + 1, maxEnd + 1)
}
```

## 解答三：

因为回文是从中心对称的，所以可以选择一个中心，进行左右扩展，判断左右字符是否相等即可。

因为"bb"这种也是回文，因此每一个字符之间的空隙也需要当做中心进行扩展判断。所以总共需要判断2n-1次，中心扩展是也需要进行一次循环，因此该算法的复杂度是 O(n^2)。

```kotlin
fun longestPalindrome3(s: String): String {
	if (s.isEmpty()) return ""
	var start = 0
	var end = 0
	for (i in 0 until s.length) {
		var len1 = expandAroundCenter(s, i, i)
		var len2 = expandAroundCenter(s, i, i + 1)
		var len = Math.max(len1, len2)
		if (len > end - start) {
			start = i - (len - 1) / 2
			end = i + len / 2
		}
	}
	return s.substring(start, end + 1)
}

fun expandAroundCenter(s: String, left: Int, right: Int): Int {
	var l = left
	var r = right
	while (l >= 0 && r < s.length && s[l] == s[r]) {
		l--
		r++
	}
	return r - l - 1
}
```

## 解答四：

Manacher's Algorithm，这个算法将复杂度降低到了线性，也就是时间复杂度是 O(n)，空间复杂度也是 O(n)。