package com.ccooy.testonly.leet1_20

class Solution8 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val s1 = "    -42"
            println(atoi(s1))
            val s2 = "  -4193 with words"
            println(atoi(s2))
            val s3 = "words and 987"
            println(atoi(s3))
            val s4 = "91283472332"
            println(atoi(s4))
        }

        fun atoi(str: String): Int {
            var sign = 1
            var hasSign = false
            var result = 0
            if (str.isEmpty()) return 0
            for (c in str) {
                if (!hasSign && c == '-') {
                    hasSign = true
                    sign = -1
                    continue
                }
                if (!hasSign && c == '+') {
                    hasSign = true
                    sign = 1
                    continue
                }
                if (!hasSign && c == ' ') {
                    continue
                }
                if (c in '0'..'9') {
                    hasSign = true
                    var pop = c - '0'
                    if (result * sign > Int.MAX_VALUE / 10 || (result * sign == Int.MAX_VALUE / 10 && pop * sign > 7)) {
                        return Int.MAX_VALUE
                    }
                    if (result * sign < Int.MIN_VALUE / 10 || (result * sign == Int.MIN_VALUE / 10 && pop * sign < -8)) {
                        return Int.MIN_VALUE
                    }
                    result = result * 10 + pop
                } else {
                    return result * sign
                }
            }
            return result * sign
        }
    }
}