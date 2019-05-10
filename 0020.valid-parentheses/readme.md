# 20.有效的括号

## 题目：

给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

	示例 1:
	输入: "()"
	输出: true

	示例 2:
	输入: "()[]{}"
	输出: true

	示例 3:
	输入: "(]"
	输出: false

	示例 4:
	输入: "([)]"
	输出: false

	示例 5:
	输入: "{[]}"
	输出: true

## 解答一：

非常独特的方法，只有三种括号又必须配对，对于有效的字符串来说，无论怎么乱序，总有一对括号是相邻且对应的。所以必定能找到“()”、“[]”、“{}”三种情况，将其替换成空，剩下的字符串又会出现配对的情况，不停替换下去，直到字符串成为空。就像消消乐一样。

```kotlin
fun isValid(s: String): Boolean {
	var temp = s
	while (temp.contains("()") || temp.contains("[]") || temp.contains("{}")) {
		temp = temp.replace("()", "")
		temp = temp.replace("[]", "")
		temp = temp.replace("{}", "")
	}
	return temp == ""
}
```

## 解答二：

传统的做法还是使用栈，左括号进栈，右括号跟左括号比较，如果配对就出栈，直到最后栈为空。另外括号能够配对的话，长度必定是2的倍数，所以直接先判断一下长度是否有效。时间复杂度O(n)，空间复杂度O(n)。

```kotlin
fun isValid2(s: String): Boolean {
	if (s.length % 2 != 0) return false
	val mappings = mutableMapOf(')' to '(', ']' to '[', '}' to '{')
	val stack = Stack<Char>()
	for (c in s) {
		if (mappings.containsKey(c)) {
			val topElement = if (stack.empty()) '#' else stack.pop()
			if (topElement != mappings[c]) {
				return false
			}
		} else {
			stack.push(c)
		}
	}
	return stack.isEmpty()
}
```

## 解答三：

用LinkedList模拟栈，效率比Stack要稍好一点。

```kotlin
class Parentheses {
	val stack = LinkedList<Char>()

	fun process(p: Char): Boolean {
		return when (p) {
			'(', '[', '{' -> {
				stack.push(p)
				true
			}
			')', ']', '}' -> {
				popup(p)
			}
			else -> false
		}
	}

	val size: Int
		get() {
			return stack.size
		}

	private fun popup(p: Char): Boolean {
		if (stack.isEmpty()) return false
		val op = stack.pop()
		return when (p) {
			')' -> op == '('
			']' -> op == '['
			'}' -> op == '{'
			else -> false
		}
	}
}
```

```kotlin
fun isValid(s: String): Boolean {
	if (s.length % 2 != 0) return false
	val stack = Parentheses()
	for (c in s) {
		if (stack.process(c).not()) return false
	}
	if (stack.size != 0) return false
	return true
}
```