# 13.罗马数字转整数

## 题目：

罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

	字符          数值
	I             1
	V             5
	X             10
	L             50
	C             100
	D             500
	M             1000

例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

	示例 1:
	输入: "III"
	输出: 3

	示例 2:
	输入: "IV"
	输出: 4

	示例 3:
	输入: "IX"
	输出: 9

	示例 4:
	输入: "LVIII"
	输出: 58
	解释: L = 50, V= 5, III = 3.

	示例 5:
	输入: "MCMXCIV"
	输出: 1994
	解释: M = 1000, CM = 900, XC = 90, IV = 4.

## 解答：

按照罗马数字按位加即可，利用到罗马数字的规则，一般情况是表示数字大的字母在前，数字小的字母在后。如果数字大的在后，小的在前则需要做减法，大的数减去小的数。

```kotlin
fun romanToInt(s: String): Int {
	var result = 0
	val valueMap = mapOf(
		Pair('M', 1000),
		Pair('D', 500),
		Pair('C', 100),
		Pair('L', 50),
		Pair('X', 10),
		Pair('V', 5),
		Pair('I', 1)
	)
	if (s.isEmpty()) return result
	for (i in 0 until s.length) {
		val cur = valueMap[s[i]] ?: 0
		if (i == s.length - 1) {
			result += cur
		} else {
			val next = valueMap[s[i + 1]] ?: 0
			if (cur < next) {
				result -= cur
			} else {
				result += cur
			}
		}
	}
	return result
}
```



