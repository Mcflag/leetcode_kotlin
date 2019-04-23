## 题目：

给定一个字符串，请你找出其中不含有重复字符的 **最长子串** 的长度。

示例 1:

	输入: "abcabcbb"
	输出: 3 
	解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。

示例 2:

	输入: "bbbbb"
	输出: 1
	解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。

示例 3:

	输入: "pwwkew"
	输出: 3
	解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。请注意，你的答案必须是 **子串** 的长度，"pwke" 是一个子序列，不是子串。

## 解答：

Sliding Window，滑动窗口方法，一次循环即可。

```kotlin
fun lengthOfLongestSubstring(s: String): Int {
	var result = 0
	var start = 0
	var map = HashMap<Char, Int>()
	for ((i, v) in s.withIndex()) {
		if (map.contains(v)) {
			start = Math.max(map[v] ?: 0, start)
		}
		result = Math.max(result, i - start + 1)
		map[v] = i + 1
	}
	return result
}
```

