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
```

```kotlin
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

## 解答四：

利用前缀树来进行前缀的比较。关于前缀树有三个特征：

1. 根节点不包含字符，除根节点外每一个节点都只包含一个字符。
2. 从根节点到某一节点，路径上经过的字符连接起来，为该节点对应的字符串。
3. 每个节点的所有子节点包含的字符都不相同。

了解了前缀树，求最长前缀的问题就可变为满足以下几个条件即可：

1. 这是所查询的字符串 q 的一个前缀。
2. 路径上的每一个节点都有且仅有一个孩子。 否则，找到的路径就不是所有字符串的公共前缀。
3. 路径不包含被标记成某一个键值字符串结尾的节点。 因为最长公共前缀不可能比某个字符串本身长。

本题的算法就是先构成一颗字典树，然后在树中匹配q的前缀，从根节点遍历这颗字典树，直到因为不能满足某个条件而不能再遍历为止。

时间复杂度包含了建立字典树的时间`O(m*n)`，查找字符串的时间`O(m)`，空间复杂度是`O(m*n)`。

```kotlin
fun longestCommonPrefix(strs: Array<String>): String {
	if (strs.isEmpty()) return ""
	if (strs.size == 1) return strs[0]
	val trie = Trie()
	for (i in 1 until strs.size) {
		trie.insert(strs[i])
	}
	return trie.searchLongestPrefix(strs[0])
}
```

字典树节点

```kotlin
class TrieNode {

	private val links: Array<TrieNode?>

	private val r = 26
	var size = 0

	var isEnd: Boolean = false

	init {
		links = arrayOfNulls(r)
	}

	fun containsKey(ch: Char): Boolean {
		return links[ch - 'a'] != null
	}

	fun getLink(ch: Char): TrieNode? {
		return links[ch - 'a']
	}

	fun putLink(ch: Char, node: TrieNode) {
		links[ch - 'a'] = node
		size++
	}

	fun setEnd() {
		isEnd = true
	}
}
```

构建字典树

```kotlin
class Trie {
	private val root: TrieNode = TrieNode()

	// Inserts a word into the trie.
	fun insert(word: String) {
		var node = root
		for (i in 0 until word.length) {
			val currentChar = word[i]
			if (!node.containsKey(currentChar)) {
				node.putLink(currentChar, TrieNode())
			}
			node = node.getLink(currentChar)!!
		}
		node.setEnd()
	}

	fun searchPrefix(word: String): TrieNode? {
		var node = root
		for (i in 0 until word.length) {
			val curLetter = word[i]
			if (node.containsKey(curLetter)) {
				node = node.getLink(curLetter)!!
			} else {
				return null
			}
		}
		return node
	}

	fun searchLongestPrefix(word: String): String {
		var node = root
		val prefix = StringBuilder()
		for (i in 0 until word.length) {
			val curLetter = word[i]
			if (node.containsKey(curLetter) && node.size == 1 && !node.isEnd) {
				prefix.append(curLetter)
				node = node.getLink(curLetter)!!
			} else
				return prefix.toString()
		}
		return prefix.toString()
	}

	fun search(word: String): Boolean {
		val node = searchPrefix(word)
		return node != null && node.isEnd
	}

	fun startsWith(prefix: String): Boolean {
		val node = searchPrefix(prefix)
		return node != null
	}
}
```