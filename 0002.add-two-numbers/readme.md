## 题目：

给出两个 **非空** 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 **逆序** 的方式存储的，并且它们的每个节点只能存储 **一位** 数字。

如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

**示例：**

**输入：** (2 -> 4 -> 3) + (5 -> 6 -> 4)

**输出：** 7 -> 0 -> 8

**原因：** 342 + 465 = 807

## 解答：

两个链表按顺序取值并求和，用一个变量存进位即可。

```kotlin
class ListNode(var `val`: Int) {
	var next: ListNode? = null
}

	fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
		var result = ListNode(0)
		var node1 = l1
		var node2 = l2
		var t = result
		var sum = 0
		while (node1 != null || node2 != null) {
			sum /= 10
			if (node1 != null) {
				sum += node1.`val`
				node1 = node1.next
			}
			if (node2 != null) {
				sum += node2.`val`
				node2 = node2.next
			}
			t.next = ListNode(sum%10)
			t = t.next as ListNode
		}
		if (sum / 10 != 0) t.next = ListNode(1)
		return result.next
	}
```

## 扩展：

如果链表中的数字不是按逆序存储的呢？例如：

	(3 -> 4 -> 2) + (4 -> 6 -> 5) = 8 -> 0 -> 7

对于这个问题首先考虑的是归化成刚才解决的问题，实际就是将两个链表倒序之后按照上面的方法相加即可。倒序的方法如下：

	fun reverseLinkedList(head: ListNode?): ListNode? {
		var prev: ListNode? = null
		var current: ListNode? = head
		var next: ListNode? = head

		while (current != null) {
			next = current.next
			current.next = prev
			prev = current
			current = next
		}
		return prev
	}
