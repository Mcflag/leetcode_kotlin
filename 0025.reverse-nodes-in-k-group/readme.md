# 25.k个一组翻转链表

## 题目：

给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

示例 :

给定这个链表：1->2->3->4->5

当 k = 2 时，应当返回: 2->1->4->3->5

当 k = 3 时，应当返回: 3->2->1->4->5

说明 :

* 你的算法只能使用常数的额外空间。
* 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

## 解答：

跟24题类似，迭代或者递归的方法。不过题目要求只能使用常数的额外空间，就不能使用递归了，用迭代实现。时间复杂度O(n)，空间复杂度O(1)。

```kotlin
fun reverseKGroup(head: ListNode?, k: Int): ListNode? {
    if (head == null) return null
    var dummy = ListNode(0)
    var subHead = head
    dummy.next = head
    var tail = dummy
    var toNull = head
    while (subHead != null) {
        var i = k
        while (i - 1 > 0) {
            toNull = toNull?.next
            if (toNull == null) {
                return dummy.next
            }
            i--
        }
        var temp = toNull?.next
        toNull?.next = null
        var newSub = reverse(subHead)
        tail.next = newSub
        tail = subHead
        subHead = temp
        toNull = subHead
        tail.next = subHead
    }
    return dummy.next
}
```

```kotlin
fun reverse(head: ListNode?): ListNode? {
    var h = head
    var current: ListNode? = null
    while (h != null) {
        val next = h.next
        h.next = current
        current = h
        h = next
    }
    return current
}
```



