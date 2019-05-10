package com.ccooy.testonly.leet21_40

class Solution21 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var a1 = ListNode(1)
            var a2 = ListNode(2)
            var a3 = ListNode(4)
            a1.next = a2
            a2.next = a3

            var b1 = ListNode(1)
            var b2 = ListNode(3)
            var b3 = ListNode(4)
            b1.next = b2
            b2.next = b3

            var result = mergeTwoLists(a1, b1)
            while (result != null) {
                println(result.`val`)
                result = result.next
            }
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
    }

    class ListNode(val `val`: Int) {
        var next: ListNode? = null
    }
}