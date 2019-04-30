# 10.正则表达式匹配

## 题目：

给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。

	'.' 匹配任意单个字符。
	'*' 匹配零个或多个前面的元素。

匹配应该覆盖整个字符串 (s) ，而不是部分字符串。

说明:
	s 可能为空，且只包含从 a-z 的小写字母。
	p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。

示例 1:

	输入:
	s = "aa"
	p = "a"
	输出: false
	解释: "a" 无法匹配 "aa" 整个字符串。

示例 2:

	输入:
	s = "aa"
	p = "a*"
	输出: true
	解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。

示例 3:

	输入:
	s = "ab"
	p = ".*"
	输出: true
	解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。

示例 4:

	输入:
	s = "aab"
	p = "c*a*b"
	输出: true
	解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。

示例 5:

	输入:
	s = "mississippi"
	p = "mis*is*p*."
	输出: false

## 解答一：

递归比较，按顺序一个一个比较。没有符号的情况下string取一个字符，pattern取一个字符比较；有“.”的情况下直接跳过一位；有“`*`”的情况分两种，一是pattern直接跳过2位，比如“c`*`”可以匹配0个c，二是直接跳过一位，但是pattern不用跳过仍然比较“`*`”号前的一个字母。

取子串substring函数的复杂度是 O(n)，按位比较的算法虽然是递归但是总体每一位都比较了一次，所以复杂度也是 O(n)，算法总复杂度是O(n^2)。
空间复杂度的话，n层递归，申请n个字符串空间，所以空间复杂度也是O(n^2)。

```kotlin
fun isMatch(s: String, p: String): Boolean {
	if (p.isEmpty()) return s.isEmpty()
	var b = !s.isEmpty() && (p[0] == s[0] || p[0] == '.')
	if (p.length >= 2 && p[1] == '*') {
		return isMatch(s, p.substring(2)) || (b && isMatch(s.substring(1), p))
	} else {
		return b && isMatch(s.substring(1), p.substring(1))
	}
}
```

## 解答二：

动态规划，从字符串s和字符模式p的末尾开始比较。一个二维数组dp用来存直到相应位置是否匹配的boolean值。从`dp[s.length][p.length]`开始，最后的结果在`dp[0][0]`中。这样的算法复杂度是 O(n^2)，空间复杂度也是 O(n^2)。

不过空间复杂度可以经过优化，因为我们只需要知道第i个和第i+1个的情况，所以并不需要`M*N`的数组，只需要一个`2*n`的数组即可。空间复杂度可以变为O(n)。

```kotlin
fun isMatch(s: String, p: String): Boolean {
	val dp = Array(2) { BooleanArray(p.length + 1) }
	dp[s.length % 2][p.length] = true

	for (i in s.length downTo 0) {
		for (j in p.length downTo 0) {
			if (i == s.length && j == p.length) continue
			var first_match = i < s.length && j < p.length && (s[i] == p[j] || p[j] == '.')
			if (j + 1 < p.length && p[j + 1] == '*') {
				dp[i % 2][j] = dp[i % 2][j + 2] || (first_match && dp[(i + 1) % 2][j])
			} else {
				dp[i % 2][j] = first_match && dp[(i + 1) % 2][j + 1]
			}
		}
	}
	return dp[0][0]
}

/*优化前的方法*/
fun isMatch(s: String, p: String): Boolean {
	val dp = Array(s.length) { BooleanArray(p.length + 1) }
	dp[s.length][p.length] = true

	for (i in s.length downTo 0) {
		for (j in p.length downTo 0) {
			if (i == s.length && j == p.length) continue
			var first_match = i < s.length && j < p.length && (s[i] == p[j] || p[j] == '.')
			if (j + 1 < p.length && p[j + 1] == '*') {
				dp[i][j] = dp[i][j + 2] || (first_match && dp[i + 1][j])
			} else {
				dp[i][j] = first_match && dp[i + 1][j + 1]
			}
		}
	}
	return dp[0][0]
}
```




