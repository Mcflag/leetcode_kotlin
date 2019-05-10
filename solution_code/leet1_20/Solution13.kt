package com.ccooy.testonly.leet1_20

class Solution13 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val a = "LVIII"
            println(romanToInt(a)) //ans:58
            val b = "MCMXCIV"
            println(romanToInt(b)) //ans:1994
        }

        fun romanToInt(s: String): Int {
            var result = 0
            val valueMap = mapOf(
                Pair('M', 1000),
                Pair('D', 500),
                Pair('C', 100),
                Pair('L', 50),
                Pair('X', 10),
                Pair('V', 5),
                Pair('I', 1)
            )
            if (s.isEmpty()) return result
            for (i in 0 until s.length) {
                val cur = valueMap[s[i]] ?: 0
                if (i == s.length - 1) {
                    result += cur
                } else {
                    val next = valueMap[s[i + 1]] ?: 0
                    if (cur < next) {
                        result -= cur
                    } else {
                        result += cur
                    }
                }
            }
            return result
        }
    }

}