# 21.合并两个有序链表

## 题目：

将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

	输入：1->2->4, 1->3->4
	输出：1->1->2->3->4->4

## 解答：

从两个链表头开始迭代比较，逐个改变原链表节点的指向，用一个新的链表头存答案。时间复杂度O(m+n)，空间复杂度O(1)。

```kotlin
class ListNode(val `val`: Int) {
	var next: ListNode? = null
}
```

```kotlin
fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
    if (l1 == null) return l2
    if (l2 == null) return l1
    var head = ListNode(0)
    var ans = head
    var temp1 = l1
    var temp2 = l2
    while (temp1 != null && temp2 != null) {
        if (temp1.`val` < temp2.`val`) {
            head.next = temp1
            head = head.next!!
            temp1 = temp1.next
        } else {
            head.next = temp2
            head = head.next!!
            temp2 = temp2.next
        }
    }
    if (temp1 == null) {
        head.next = temp2
    }
    if (temp2 == null) {
        head.next = temp1
    }
    return ans.next
}
```



