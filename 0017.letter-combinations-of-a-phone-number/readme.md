# 17.电话号码的字母组合

## 题目：

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

	2->a,b,c
	3->d,e,f
	4->g,h,i
	5->j,k,l
	6->m,n,o
	7->p,q,r,s
	8->t,u,v
	9->w,x,y,z

示例:

	输入："23"
	输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].

说明:
尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。

## 解答：

用队列的思路，从队列头取字符串与下一个字符串每一位字符组合，再添加进队尾，直到取出字符串长度超过当前所需的字符串，判断一个循环完毕，进行下一个循环。这样的时间复杂度应该是`O(3^n*4^m)`，因为字符数只能够是3或者4位，结果是排列组合的结果，那么循环总次数就是`3^n*4^m`，所以最后循环的次数会指数增加。空间复杂度是`O(3^n*4^n)`

```kotlin
fun letterCombinations(digits: String): List<String> {
	var result = LinkedList<String>()
	if (digits.isEmpty()) return result
	var dict = arrayOf("0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
	result.add("")
	for ((i, d) in digits.withIndex()) {
		while (result.peek().length == i) {
			var t = result.remove()
			dict[d - '0'].forEach {
				result.add(t + it)
			}
		}
	}
	return result
}
```



