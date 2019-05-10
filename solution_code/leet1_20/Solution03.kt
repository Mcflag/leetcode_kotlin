package com.ccooy.testonly.leet1_20

class Solution03 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var s = "abcdredfgbhijk"
            println(lengthOfLongestSubstring(s))

        }

        fun lengthOfLongestSubstring(s: String): Int {
            var result = 0
            var start = 0
            var map = HashMap<Char, Int>()
            for ((i, v) in s.withIndex()) {
                if (map.contains(v)) {
                    start = Math.max(start, map[v] ?: 0)
                }
                result = Math.max(result, i - start + 1)
                map[v] = i + 1
            }
            return result
        }
    }
}