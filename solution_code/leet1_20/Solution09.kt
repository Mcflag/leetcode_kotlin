package com.ccooy.testonly.leet1_20

class Solution09 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var i = -121
            println(isPalindrome1(i))
            var i2 = 121
            println(isPalindrome1(i2))
            var i3 = 10
            println(isPalindrome1(i3))
        }

        fun isPalindrome(x: Int): Boolean {
            return x.toString() == x.toString().reversed()
        }

        fun isPalindrome1(x: Int): Boolean {
            if (x < 0) {
                return false
            }
            if ((x % 10) == 0 && (x != 0)) return false
            var temp = x
            var reverse = 0
            while (temp != 0) {
                val pop = temp % 10
                temp /= 10
                reverse = reverse * 10 + pop
            }
            return x == reverse
        }
    }
}