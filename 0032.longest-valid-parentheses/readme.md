# 32.最长有效括号

## 题目：

给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。

示例 1:
	输入: "(()"
	输出: 2
	解释: 最长有效括号子串为 "()"

示例 2:
	输入: ")()())"
	输出: 4
	解释: 最长有效括号子串为 "()()"

## 解答一：

对字符串遍历，进行括号有效性验证，记录最大有效长度，倒序同样进行一次遍历，取最大值。这样时间复杂度O(n)，空间复杂度O(1)。

```kotlin
fun longestValidParentheses(s: String): Int {
    var left = 0
    var right = 0
    var max = 0
    for (c in s) {
        if (c == '(') left++ else right++
        if (left == right) {
            max = Math.max(max, 2 * right)
        } else if (right > left) {
            left = 0
            right = 0
        }
    }
    left = 0
    right = 0
    for (i in s.length - 1 downTo 0) {
        if (s[i] == '(') left++ else right++
        if (left == right) {
            max = Math.max(max, 2 * left)
        } else if (left > right) {
            left = 0
            right = 0
        }
    }
    return max
}
```

## 解答二：

对于这种括号匹配的问题，常规解法就是栈。扫描到左括号入栈，扫描到右括号出栈，表示栈顶的左括号匹配到了右括号。

出栈有两种情况，栈不空，那么就用当前的位置减去栈顶的存的位置，然后就得到当前合法序列的长度，然后更新一下最长长度。栈是空的，说明之前没有与之匹配的左括号，那么就将当前的位置入栈。

算法时间复杂度O(n)，空间复杂度O(n)。

```kotlin
fun longestValidParentheses(s: String): Int {
    var max = 0
    var numStack = Stack<Int>()
    numStack.push(-1)
    for (i in 0 until s.length) {
        if (s[i] == '(') {
            numStack.push(i)
        } else {
            numStack.pop()
            if (numStack.empty()) {
                numStack.push(i)
            } else {
                max = Math.max(max, i - numStack.peek())
            }
        }
    }
    return max
}
```

## 解答三：

dp动态规划算法。我们用dp[i]表示以i结尾的最长有效括号；

1. 当s[i]为`(`,dp[i]必然等于0,因为不可能组成有效的括号;

2. 那么s[i]为`)`

* 当 s[i-1]为`(`，那么dp[i]=dp[i-2]+2；

* 当 s[i-1]为`)`并且s[i-dp[i-1]-1]为`(`，那么dp[i]=dp[i-1]+2+dp[i-dp[i-1]-2]；

算法时间复杂度O(n)，空间复杂度O(n)。

```kotlin
fun longestValidParentheses(s: String): Int {
    var max = 0
    var dp = Array(s.length) { 0 }
    for (i in 1 until s.length) {
        if (s[i] == ')') {
            if (s[i - 1] == '(') {
                dp[i] = (if (i >= 2) dp[i - 2] else 0) + 2
            } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                dp[i] = dp[i - 1] + (if (i - dp[i - 1] >= 2) dp[i - dp[i - 1] - 2] else 0) + 2
            }
            max = Math.max(max, dp[i])
        }
    }
    return max
}
```