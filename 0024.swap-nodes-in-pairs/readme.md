# 24.两两交换链表中的节点

## 题目：

给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

示例:给定 1->2->3->4, 你应该返回 2->1->4->3.

## 解答一：

迭代翻转链表。时间复杂度O(n)，空间复杂度O(1)。

```kotlin
fun swapPairs(head: ListNode?): ListNode? {
    var dummy = ListNode(0)
    dummy.next = head
    var point = dummy
    while (point.next != null && point.next?.next != null) {
        var swap1 = point.next
        var swap2 = point.next?.next
        point.next = swap2
        swap1?.next = swap2?.next
        swap2?.next = swap1
        point = swap1!!
    }
    return dummy.next
}
```

## 解答二：

递归方式处理。

```kotlin
fun swapPairs(head: ListNode?): ListNode? {
    if (head == null || head.next == null) {
        return head
    }
    val n = head.next
    head.next = swapPairs(head.next?.next)
    n?.next = head
    return n
}
```


