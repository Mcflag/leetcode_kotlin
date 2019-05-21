package com.ccooy.testonly.leet21_40

class Solution29 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var dividend = 10
            var divisor = -3
            println(divide(dividend, divisor))
        }

        fun divide(dividend: Int, divisor: Int): Int {
            if (dividend == 0) return 0
            if (dividend == Int.MIN_VALUE && divisor == -1) return Int.MAX_VALUE
            var positive = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0)
            var mdividend = Math.abs(dividend.toLong())
            var mdivisor = Math.abs(divisor.toLong())
            var count = 0
            for (i in 31 downTo 0) {
                if (mdividend shr i >= mdivisor) {
                    count += 1 shl i
                    mdividend -= mdivisor shl i
                }
            }
            return if (positive) count else 0 - count
        }
    }
}