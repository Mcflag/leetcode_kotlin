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

Manacher's Algorithm，这个算法将复杂度降低到了线性，也就是时间复杂度是 O(n)，空间复杂度也是 O(n)。算法思路是：

1. 对字符串预处理，每个字符间增加#号，这是为了统一奇数长度和偶数长度的字符串，头尾各增加不一样的字符^和&，目的是为了防止越界。如：

	"cbcbcbde"扩展成为"^#c#b#c#b#c#b#d#e#$"
	
2. 按顺序遍历字符串，每一个字符向两边扩展比较，用一个数组保存每个位置向两边扩展得到的回文串的长度半径。

	"^#a#b#b#a#$"保存一个数组P=[0,0,1,0,1,4,1,0,1,0,0]

3. 没找到一个回文串时，就能够得到一个中点C，回文串左边界L，右边界R，C点左边的P的值是知道的，要求C点右边的P值。

4. 如果i在C到R之间，那么L到C之间有i的镜像点i_mirror，且P[i_mirror]的值是知道的。可以直接P[i]=P[i_mirror]减少循环。

5. 有几种情况不能直接赋值，一个是i点的回文右边界超出了R，这种情况下可以断定回文长度至少是R-i，在R之外的点是否仍然是回文还需要暴力查。

	这时可以用P[i]=Math.min(R-i, P[i_mirror])统一处理i点回文串右边界小于等于R，或者超过R的情况。

6. 第二种是i_mirror超出了字符串左边界的情况，所以需要接着暴力查进行扩展。

7. 第三种是i等于R或者超出了R，这种情况先初始化P[i]=0，然后正常扩展，查询结束之后重新设置C和边界R。

8. 最后从得到的P数组中得到最大值的下标i，以及最大值P[i]，那么元字符串中的回文串开始的索引start=(i-P[i])/2，回文串长度为P[i]。

虽然算法中感觉有两个循环，一个是外部按字符串索引，另一个是在每个索引处进行扩展比较，但是实际上每次都是从R+1开始扩展，随后就改变了R，那么每个字符最多只遍历了2次，当然如果原字符串长度为n，我们总共遍历2*(2n+3)次，即使算上后面遍历P数组找最大的遍历，总算法仍然是线性的。所以算法复杂度确实是 O(n)。


```kotlin
fun preProcess(s: String): String {
	if (s.length == 0) {
		return "^$"
	}
	var result = "^"
	for (a in s) {
		result += "#$a"
	}
	result += "#$"
	return result
}
fun longestPalindrome(s: String): String {
	var str = preProcess(s)
	var n = str.length
	var p = IntArray(n)
	var c = 0
	var r = 0
	for (i in 1 until n - 1) {
		var i_mirror = 2 * c - i
		if (r > i) {
			p[i] = Math.min(r - i, p[i_mirror]) //防止超出r
		} else {
			p[i] = 0 //r=i的情况
		}
		while (str[i + 1 + p[i]] == str[i - 1 - p[i]]) {
			p[i]++
		}
		if (i + p[i] > r) {
			c = i
			r = i + p[i]
		}
	}
	var maxLen = 0
	var centerIndex = 0
	for (i in 1 until n - 1) {
		if (p[i] > maxLen) {
			maxLen = p[i]
			centerIndex = i
		}
	}
	var start = (centerIndex - maxLen) / 2
	return s.substring(start, start + maxLen)
}
```


