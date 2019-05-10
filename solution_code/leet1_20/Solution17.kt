package com.ccooy.testonly.leet1_20

import java.util.*

class Solution17 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var a = "23"
            for (i in letterCombinations(a)) {
                println(i)
            }
        }

        fun letterCombinations(digits: String): List<String> {
            var result = LinkedList<String>()
            if (digits.isEmpty()) return result
            var dict = arrayOf("0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz")
            result.add("")
            for ((i, d) in digits.withIndex()) {
                while (result.peek().length == i) {
                    var t = result.remove()
                    dict[d - '0'].forEach {
                        result.add(t + it)
                    }
                }
            }
            return result
        }
    }
}
