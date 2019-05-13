# 23.合并K个排序链表

## 题目：

合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

示例:

	输入:
	[
	  1->4->5,
	  1->3->4,
	  2->6
	]

	输出: 1->1->2->3->4->4->5->6

## 解答一：

每一个链表的开头一起进行比较，最小的保存起来。或者直接改变最小链表节点的指向。

假设有k个链表，最长的链表长度为n，时间复杂度为O(kn)，空间复杂度O(1)。

```kotlin
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    var minIndex = 0
    val result: ListNode? = ListNode(0)
    var head = result
    while (true) {
        var isBreak = true
        var min = Int.MAX_VALUE
        for (i in 0 until lists.size) {
            if (lists[i] != null) {
                lists[i]?.let {
                    if (it.`val` < min) {
                        minIndex = i
                        min = it.`val`
                    }
                }
                isBreak = false
            }
        }
        if (isBreak) {
            break
        }
        head?.next = lists[minIndex]
        head = head?.next
        lists[minIndex] = lists[minIndex]?.next
    }
    head?.next = null
    return result?.next
}
```

## 解答二：

两两合并，从列表中将链表两两合并，合并的链表再次两两合并，直到合并成一个链表即为答案。假设每个链表的长度都是n，那么时间复杂度是`O(n*logk)`，空间复杂度是O(1)。

```kotlin
fun mergeKLists2(lists: Array<ListNode?>): ListNode? {
    if (lists.isEmpty()) return null
    var interval = 1
    while (interval < lists.size) {
        var i = 0
        while (i + interval < lists.size) {
            lists[i] = mergeTwoLists(lists[i], lists[i + interval])
            i += interval * 2
        }
        interval *= 2
    }
    return lists[0]
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

## 解答三：

优先队列。建立一个满足优先队列条件的堆，放入k的数据，每次从中取出最小的，然后在其中添加新的数，并由其自动调整为符合条件的优先队列。

```kotlin
fun mergeKLists(lists: Array<ListNode?>): ListNode? {
    val cmp = Comparator<ListNode> { o1, o2 ->
        o1.`val` - o2.`val`
    }
    val q = PriorityQueue<ListNode>(lists.size, cmp)
    for (i in lists) {
        if (i != null) q.add(i)
    }
    val head = ListNode(0)
    var point = head
    while (q.isNotEmpty()) {
        point.next = q.poll()
        point = point.next!!
        if (point.next != null) q.add(point.next)
    }
    return head.next
}
```