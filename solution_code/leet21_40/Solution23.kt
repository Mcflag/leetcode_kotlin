package com.ccooy.testonly.leet21_40

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

        fun mergeKLists(lists: Array<ListNode?>): ListNode? {

        }
    }

    class ListNode(val `val`: Int) {
        var next: ListNode? = null
    }
}