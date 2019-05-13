package com.ccooy.testonly.leet21_40

import android.annotation.SuppressLint
import java.util.*

class Solution23 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val a1 = ListNode(1)
            val a2 = ListNode(4)
            val a3 = ListNode(5)
            a1.next = a2
            a2.next = a3

            val b1 = ListNode(1)
            val b2 = ListNode(3)
            val b3 = ListNode(4)
            b1.next = b2
            b2.next = b3

            val c1 = ListNode(2)
            val c2 = ListNode(6)
            c1.next = c2

            val lista: Array<ListNode?> = arrayOf(a1, b1, c1)
            var result = mergeKLists(lista)
            while (result != null) {
                println(result.`val`)
                result = result.next
            }
        }

        fun mergeKLists1(lists: Array<ListNode?>): ListNode? {
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

        @SuppressLint("NewApi")
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
    }

    class ListNode(val `val`: Int) {
        var next: ListNode? = null
    }
}