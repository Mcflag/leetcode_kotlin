# 22.括号生成

## 题目：

给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。

例如，给出 n = 3，生成结果为：

	[
	  "((()))",
	  "(()())",
	  "(())()",
	  "()(())",
	  "()()()"
	]

## 解答一：

暴力列举所有的情况，每一位有左括号和右括号两种情况，总共 2n 位，所以总共 2^2n=4^n 种情况。判断是否有效的复杂度n，时间复杂度 `O(n*4^n)`，空间复杂度`O(n*4^n)`。

```kotlin
fun generateParenthesis(n: Int): List<String> {
    var combinations = ArrayList<String>()
    generateAll(Array(n * 2) { ' ' }, 0, combinations)
    return combinations
}
```

```kotlin
fun generateAll(current: Array<Char>, pos: Int, result: ArrayList<String>) {
    if (pos == current.size) {
        if (isValid(current)) {
            result.add(current.joinToString(""))
        }
    } else {
        current[pos] = '('
        generateAll(current, pos + 1, result)
        current[pos] = ')'
        generateAll(current, pos + 1, result)
    }
}
```

```kotlin
fun isValid(current: Array<Char>): Boolean {
    var balance = 0
    for (c in current) {
        if (c == '(') balance++ else balance--
        if (balance < 0) return false
    }
    return balance == 0
}
```

## 解答二：

回溯法，只有在字符串有效的情况下才添加“(”和“)”，而不是全部生成完之后再检查是否有效。

我们的复杂度分析依赖于理解 generateParenthesis(n) 中有多少个元素。事实证明这是第 n 个卡塔兰数 `(1/(n+1))*C(2n,n)`。
实际上原理就是总共有的排列组合是`C(2n,n)`，但是其中无效的情况有`C(2n,n-1)`，那么总共的有效数是`C(2n,n)-C(2n,n-1) = (1/(n+1))*C(2n,n)`，它由 `(4^n)/(n*sqrt(n))` 渐近界定的。

时间复杂度：`O((4^n)/sqrt(n))`，在回溯过程中，每个有效序列最多需要 n 步。

空间复杂度：`O((4^n)/sqrt(n))`，如上所述，并使用 O(n) 的空间来存储序列。

```kotlin
fun generateParenthesis(n: Int): List<String> {
    var ans = ArrayList<String>()
    backtrack(ans, "", 0, 0, n)
    return ans
}
```

```kotlin
fun backtrack(result: ArrayList<String>, current: String, open: Int, close: Int, max: Int) {
    if (current.length == 2 * max) {
        result.add(current)
        return
    }
    if (open < max) backtrack(result, "$current(", open + 1, close, max)
    if (close < open) backtrack(result, "$current)", open, close + 1, max)
}
```