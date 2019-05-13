package com.ccooy.testonly.leet21_40

class Solution24 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val a1 = ListNode(1)
            val a2 = ListNode(2)
            val a3 = ListNode(3)
            val a4 = ListNode(4)
            a1.next = a2
            a2.next = a3
            a3.next = a4
            var result = swapPairs(a1)
            while (result != null) {
                println(result.`val`)
                result = result.next
            }
        }

        fun swapPairs1(head: ListNode?): ListNode? {
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

        fun swapPairs(head: ListNode?): ListNode? {
            if (head == null || head.next == null) {
                return head
            }
            val n = head.next
            head.next = swapPairs(head.next?.next)
            n?.next = head
            return n
        }
    }

    class ListNode(val `val`: Int) {
        var next: ListNode? = null
    }
}