package com.ccooy.testonly.leet1_20

class Solution02 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val a = ListNode(1)
            val aa = ListNode(9)
            val aaa = ListNode(4)
            val aaaa = ListNode(2)
            a.next = aa
            aa.next = aaa
            aaa.next = aaaa

            val b = ListNode(4)
            val bb = ListNode(6)
            val bbb = ListNode(5)
            b.next = bb
            bb.next = bbb

            printListNode(reverseLinkedList(addTwoNumbers(reverseLinkedList(a), reverseLinkedList(b))))
        }

        class ListNode(val `val`: Int) {
            var next: ListNode? = null
        }

        fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
            var node1 = l1
            var node2 = l2
            var result = ListNode(0)
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
                t.next = ListNode(sum % 10)
                t = t.next as ListNode
            }
            if (sum / 10 != 0) t.next = ListNode(1)
            return result.next
        }

        fun printListNode(l: ListNode?) {
            var temp = l
            while (temp != null) {
                println(temp.`val`)
                temp = temp.next
            }
        }

        fun reverseLinkedList(head: ListNode?): ListNode? {
            var prev: ListNode? = null
            var current = head
            var next = head
            while (current != null) {
                next = current.next
                current.next = prev
                prev = current
                current = next
            }
            return prev
        }
    }
}