package com.ccooy.testonly.leet1_20

class Solution19 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var a1 = ListNode(1)
            var a2 = ListNode(2)
            var a3 = ListNode(3)
            var a4 = ListNode(4)
            var a5 = ListNode(5)
            a1.next = a2
            a2.next = a3
            a3.next = a4
            a4.next = a5
            var n = 5
            var temp: ListNode? = removeNthFromEnd(a1, n)
            while (temp != null) {
                println(temp.`val`)
                temp = temp.next
            }
        }

        class ListNode(val `val`: Int) {
            var next: ListNode? = null
        }

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

    }
}
