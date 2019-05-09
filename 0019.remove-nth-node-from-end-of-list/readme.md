# 19.删除链表的倒数第N个节点

## 题目：

给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

示例：

给定一个链表: 1->2->3->4->5, 和 n = 2.

当删除了倒数第二个节点后，链表变为 1->2->3->5.

说明：给定的 n 保证是有效的。

进阶：你能尝试使用一趟扫描实现吗？

## 解答：

本题的重点在于链表总长度不知道。最直接的方法，先遍历一遍链表，得到链表总长度L，L-n就是需要删除的节点位置，因此遍历到L-n-1的位置改变指向就行了。

更巧妙的一个方法是“快慢指针”。一个指针先遍历，遍历到n的时候第二个指针开始从头遍历，他们两个指针之间永远相隔n个，直到第一个指针遍历到末尾，第二个指针指向的就是倒数第N个节点，然后直接处理即可。

实际上第二种方式与第一种方式遍历了一样多的次数，只不过是把两次遍历合在了一趟扫描内。时间复杂度是O(n)，空间复杂度O(1)。

```kotlin
fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
	var temp = ListNode(0)
	temp.next = head
	var first: ListNode? = temp
	var second: ListNode? = temp
	for (i in 0..n) {
		first = first?.next
	}
	while (first != null) {
		first = first.next
		second = second?.next
	}
	second?.next = second?.next?.next
	return temp.next
}
```



