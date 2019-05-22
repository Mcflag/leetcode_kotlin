# 30.串联所有单词的子串

## 题目：

给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。

注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。

示例1：

	输入：
		s = "barfoothefoobarman",
		words = ["foo","bar"]
	输出：[0,9]
	解释：从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。输出的顺序不重要, [9,0] 也是有效答案。

示例2：

	输入：
		s = "wordgoodgoodgoodbestword",
		words = ["word","good","best","word"]
	输出：[]

## 解答：

从原字符串中一位一位取子串，比较子串是否符合，并记录首位的索引。比如对于“barfoothefoobarman”，关键词是"foo","bar"。那么可以取“barfoo”，“arfoot”，“rfooth”……比较每个子串是否包含所有的关键词。

因为题目中说可以不考虑顺序，那么将给个关键词存到HashMap中，并记录在words数组中出现的数量。用同样的方法分析子串，比较两个子串中关键词的数量，如果关键词相同，数量相同，那么可以确定是符合题意的，记录下来索引。如果不符合，则直接跳过。

这样做的算法复杂度是O(m*n)，空间复杂度O(m)。

优化的方式是考虑每次并不跳过1位，因为关键词的长度是相同的，可以按照关键词的长度跳过，这样就可以利用到前面判断的情况来简化后面的判断。不过这样就需要考虑开头的不足一个关键词长度的时候需要分别讨论。如“barfoothefoobarman”，关键词是3位，那么需要考虑从“b”、“a”、“r”三个位置开始的情况。

优化后的算法复杂度是O(n)，空间复杂度是O(m)。

优化前代码：

```kotlin
fun findSubstring(s: String, words: Array<String>): List<Int> {
    var res = ArrayList<Int>()
    if (words.isEmpty()) return res
    var wordLen = words[0].length
    var allWords: HashMap<String, Int> = HashMap()
    for (w in words) {
        var v = allWords[w] ?: 0
        allWords[w] = v + 1
    }
    for (i in 0 until s.length - words.size * wordLen + 1) {
        var hasWords: HashMap<String, Int> = HashMap()
        var num = 0
        while (num < words.size) {
            var word = s.substring(i + num * wordLen, i + (num + 1) * wordLen)
            if (allWords.containsKey(word)) {
                var value = hasWords[word] ?: 0
                hasWords[word] = value + 1
                if ((hasWords[word] ?: 0) > (allWords[word] ?: 0)) break
            } else {
                break
            }
            num++
        }
        if (num == words.size) {
            res.add(i)
        }
    }
    return res
}
```

优化后代码：

```kotlin
fun findSubstring(s: String, words: Array<String>): List<Int> {
    var res = ArrayList<Int>()
    if (words.isEmpty()) return res
    var wordLen = words[0].length
    var allWords: HashMap<String, Int> = HashMap()
    for (w in words) {
        var v = allWords[w] ?: 0
        allWords[w] = v + 1
    }
    for (j in 0 until wordLen) {
        var hasWords: HashMap<String, Int> = HashMap()
        var num = 0
        var i = j
        while (i < s.length - words.size * wordLen + 1) {
            var hasRemoved = false
            while (num < words.size) {
                var word = s.substring(i + num * wordLen, i + (num + 1) * wordLen)
                if (allWords.containsKey(word)) {
                    var value = hasWords[word] ?: 0
                    hasWords[word] = value + 1
                    if ((hasWords[word] ?: 0) > (allWords[word] ?: 0)) {
                        hasRemoved = true
                        var removeNum = 0
                        while ((hasWords[word] ?: 0) > (allWords[word] ?: 0)) {
                            val firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen)
                            val v = hasWords[firstWord] ?: 0
                            hasWords[firstWord] = v - 1
                            removeNum++
                        }
                        num = num - removeNum + 1
                        i += (removeNum - 1) * wordLen
                        break
                    }
                } else {
                    hasWords.clear()
                    i += (num * wordLen)
                    num = 0
                    break
                }
                num++
            }
            if (num == words.size) {
                res.add(i)
            }
            if (num > 0 && !hasRemoved) {
                val firstWord = s.substring(i, i + wordLen)
                val v = hasWords[firstWord] ?: 0
                hasWords[firstWord] = v - 1
                num -= 1
            }
            i += wordLen
        }
    }
    return res
}
```

