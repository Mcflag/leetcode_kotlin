# 6.Z字形变换

## 题目：

将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：

	L   C   I   R
	E T O E S I I G
	E   D   H   N

之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

请你实现这个将字符串进行指定行数变换的函数：

	string convert(string s, int numRows);

	示例 1:
	输入: s = "LEETCODEISHIRING", numRows = 3
	输出: "LCIRETOESIIGEDHN"

	示例 2:
	输入: s = "LEETCODEISHIRING", numRows = 4
	输出: "LDREOEIIECIHNTSG"
	解释:
	L     D     R
	E   O E   I I
	E C   I H   N
	T     S     G

## 解答一：

按照生成z字型字符串的方式按顺序遍历。用numRows个字符串存每一行的结果，储存时有从上往下和从下往上两个方向，因此可以用一个标志位表示方向。

最后将每一行的字符串连起来就是需要的结果。算法复杂度是 O(n)，空间复杂度是 O(n)。

```kotlin
fun convert(s: String, numRows: Int): String {
	if (numRows == 1) return s
	var list = ArrayList<StringBuilder>()
	for (i in 0 until Math.min(numRows, s.length)) {
		list.add(StringBuilder())
	}
	var curRow = 0
	var goingDown = false
	for (c in s) {
		list.get(curRow).append(c)
		if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown
		curRow += if (goingDown) 1 else -1
	}
	var result = StringBuilder()
	for (r in list) {
		result.append(r)
	}
	return result.toString()
}
```

## 解答二：

还有一种方式是直接通过下标找规律，考虑普通的情况，需要numRows行的话就是每隔2*numRows-2字符作为一个周期。按周期取值即可，注意判断一下最后多余的字符，下标不要越界即可。算法复杂度是 O(n)，空间复杂度是 O(n)。

```kotlin
fun convert1(s: String, numRows: Int): String {
	if (s.length == 1) return s
	var result = StringBuilder()
	var len = s.length
	var cycleLen = 2 * numRows - 2
	for (i in 0 until numRows) {
		for (j in 0 until len - i step cycleLen) {
			result.append(s[j + i])
			if (i != 0 && i != numRows - 1 && j + cycleLen - i < len) {
				result.append(s[j + cycleLen - i])
			}
		}
	}
	return result.toString()
}
```

