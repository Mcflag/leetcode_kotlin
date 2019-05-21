# 28.实现strStr()

## 题目：

实现 strStr() 函数。

给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。

示例 1:

	输入: haystack = "hello", needle = "ll"
	输出: 2

示例 2:

	输入: haystack = "aaaaa", needle = "bba"
	输出: -1

说明:

当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。

对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。

## 解答一：

BF算法：暴力匹配算法，在haystack中依序按位匹配needle的每一位。针对任意一位如果当前字符匹配成功，两个字符串的指针均向后移动一位；如果不匹配，则都要回溯。

算法复杂度O(kn)，空间复杂度O(1)。

```kotlin
fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) return 0
    var i = 0
    var j = 0
    while (i != haystack.length && j != needle.length) {
        if (haystack[i] == needle[j]) {
            i++
            j++
        } else {
            i = i - j + 1
            j = 0
        }
    }
    return if (j == needle.length) i - j else -1
}
```

## 解答二：

KMP算法：是在传统暴力法的基础上修改的算法，核心思想是找到一个办法记录下暴力法中比较过的位置的情况，在没有匹配上的时候不需要进行回溯，也不用一位一位的后移，而直接让模式字符串在源字符串中跳过一定不能匹配上的情况。代码相当于是上面暴力解法的一种优化，失配时模式串向右移动的位数为“已匹配字符数-失配字符上一位字符对应的最大长度值”。

```kotlin
fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) return 0
    var i = 0
    var j = -1
    while (i < haystack.length && j < needle.length) {
        if (j == -1 || haystack[i] == needle[j]) {
            i++
            j++
        } else {
            j = next[j]
        }
    }
    return if (j == needle.length) i - j else -1
}
```

关键在于这里保存的next数组。next数组告诉我们，当模式串中的某个字符跟文本串中的某个字符匹配失配时，模式串下一步应该跳到哪个位置。求next数组的步骤：

1. 对模式串处理，求原模式串子串对应的各个前缀后缀的公共元素的最大长度。比如对于“abab”来说，它每位的前缀后缀的公共元素的最大长度表为`[0,0,1,2]`。因为对于“aba”来说，前缀有“a”，“ab”，后缀有“a”，“ba”，两者公共子串最大长度为1。对于“abab”来说，前缀有“a”，“ab”，“aba”，后缀有“b”，“ab”，“bab”，两者公共子串最大长度为2。

2. 求next数组，将前缀后缀公共元素最大长度表每一位右移，并在首位补上-1。比如`[0,0,1,2]`变为`[-1,0,0,1]`。

为什么要求这个串？本质上是一种有限状态自动机。比如读“ababc”，如果读到了c不匹配，但是实际上可以先回退一步，到第一个b。因为如果读到了c，必定在之前能够读到一个ab，那么模式串之前的ab就不用比较就知道有了，如果再次不匹配，才转到从第一位开始匹配。

```kotlin
fun getNext(needle: String): Array<Int> {
    var next = Array(needle.length) { 0 }
    next[0] = -1
    var k = -1
    var j = 0
    while (j < needle.length - 1) {
        if (k == -1 || needle[j] == needle[k]) {
            k++
            j++
            next[j] = k
        } else {
            k = next[k]
        }
    }
    return next
}
```

对求next数组的优化。之前求next数组有一个问题，比如遇到了“abab”，如果读到了第二个b，又会会退到第一个b，但是因为第二个b不匹配，那么即使会退到第一个b一样不匹配。那么这一步就根本没有必要。所以可以直接跳过这一步，继续回退到下一步。优化过后的next数组为：

```kotlin
fun getNext(needle: String): Array<Int> {
    var next = Array(needle.length) { 0 }
    next[0] = -1
    var k = -1
    var j = 0
    while (j < needle.length - 1) {
        if (k == -1 || needle[j] == needle[k]) {
            k++
            j++
            if (needle[j] != needle[k]) {
                next[j] = k
            } else {
                next[j] = next[k]
            }
        } else {
            k = next[k]
        }
    }
    return next
}
```

KMP算法整体复杂度为O(m+n)，空间复杂度为O(n)。

## 解答三：

BM算法：是一种从模式串的尾部开始匹配的算法，在最坏的情况下，BM算法的复杂度可以为O(n)。BM算法定义了两个规则：

* 坏字符规则：当文本串中的某个字符跟模式串的某个字符不匹配时，我们称文本串中的这个失配字符为坏字符，此时模式串需要向右移动，移动的位数 = 坏字符在模式串中的位置 - 坏字符在模式串中最右出现的位置。此外，如果"坏字符"不包含在模式串之中，则最右出现位置为-1。

* 好后缀规则：当字符失配时，后移位数 = 好后缀在模式串中的位置 - 好后缀在模式串上一次出现的位置，且如果好后缀在模式串中没有再次出现，则为-1。

思路同样是为了跳过，每次失配使用两个规则，取其中更大的值，可以跳过更多的位数。

```kotlin
fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) return 0
    var right = Array(256) { -1 }
    for ((i, c) in needle.withIndex()) {
        right[c.toInt()] = i
    }
    var skip = 0
    var i = 0
    while (i <= haystack.length - needle.length) {
        skip = 0
        for (j in needle.length - 1 downTo 0) {
            if (needle[j] != haystack[i + j]) {
                skip = j - right[haystack[i + j].toInt()]
                if (skip < 1) skip = 1
                break
            }
        }
        if (skip == 0) return i
        i += skip
    }
    return -1
}
```

## 解答四：

Sunday算法：与BM相仿，时间复杂度平均为`O(n)`，最差为`O(m*n)`。算法原理是从前往后匹配，如果遇到不匹配情况判断母串S参与匹配的最后一位的下一位字符，如果该字符出现在模板串T中，选择最右出现的位置进 行对齐；否则直接跳过该匹配区域。

```kotlin
fun strStr(haystack: String, needle: String): Int {
    if (needle.isEmpty()) return 0
    var moveLength = Array(256) { needle.length + 1 }
    for ((a, c) in needle.withIndex()) {
        moveLength[c.toInt()] = needle.length - a
    }
    var i = 0
    while (i < haystack.length) {
        var j = 0
        while (j < needle.length && i + j < haystack.length && haystack[i + j] == needle[j]) {
            j++
        }

        if (j >= needle.length) return i
        if (i + needle.length > haystack.length-1) return -1
        i += moveLength[haystack[i + needle.length].toInt()]
    }
    return -1
}
```

