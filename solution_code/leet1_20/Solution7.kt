package com.ccooy.testonly.leet1_20

class Solution7 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val i1 = 1534236469
            println(reverse1(i1)) //321
            val i2 = -123
            println(reverse(i2)) //-321
            val i3 = 120
            println(reverse(i3)) //21
        }

        fun reverse(x: Int): Int {
            var temp = x
            var result: Long = 0
            while (temp != 0) {
                var pop = temp % 10
                temp /= 10
                result = result * 10 + pop
            }
            if (result > Int.MAX_VALUE || result < Int.MIN_VALUE) return 0
            return result.toInt()
        }

        fun reverse1(x: Int): Int {
            val flag = if (x > 0) 1 else -1
            var t = Math.abs(x)
            var result = 0
            var tmp = 0
            while (t != 0) {
                val last = t % 10
                tmp = tmp * 10 + last
                if ((tmp - last)/10 != result) {
                    return 0
                }
                result = tmp
                t /= 10
            }

            return result * flag
        }
    }
}