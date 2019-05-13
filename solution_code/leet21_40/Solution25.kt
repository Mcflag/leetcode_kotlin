package com.ccooy.testonly.leet21_40

class Solution25 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val a1: ListNode? = ListNode(1)
            val a2 = ListNode(2)
            val a3 = ListNode(3)
            val a4 = ListNode(4)
            a1?.next = a2
            a2.next = a3
            a3.next = a4
            var result = reverseKGroup(a1, 3)
            while (result != null) {
                println(result.`val`)
                result = result.next
            }
        }

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
    }

    class ListNode(val `val`: Int) {
        var next: ListNode? = null
    }
}