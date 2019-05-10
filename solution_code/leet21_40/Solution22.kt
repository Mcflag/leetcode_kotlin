package com.ccooy.testonly.leet21_40

class Solution22 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            for (s in generateParenthesis(3)) {
                println(s)
            }
        }

        fun generateParenthesis1(n: Int): List<String> {
            var combinations = ArrayList<String>()
            generateAll(Array(n * 2) { ' ' }, 0, combinations)
            return combinations
        }

        fun generateAll(current: Array<Char>, pos: Int, result: ArrayList<String>) {
            if (pos == current.size) {
                if (isValid(current)) {
                    result.add(current.joinToString(""))
                }
            } else {
                current[pos] = '('
                generateAll(current, pos + 1, result)
                current[pos] = ')'
                generateAll(current, pos + 1, result)
            }
        }

        fun isValid(current: Array<Char>): Boolean {
            var balance = 0
            for (c in current) {
                if (c == '(') balance++ else balance--
                if (balance < 0) return false
            }
            return balance == 0
        }

        fun generateParenthesis(n: Int): List<String> {
            var ans = ArrayList<String>()
            backtrack(ans, "", 0, 0, n)
            return ans
        }

        fun backtrack(result: ArrayList<String>, current: String, open: Int, close: Int, max: Int) {
            if (current.length == 2 * max) {
                result.add(current)
                return
            }
            if (open < max) backtrack(result, "$current(", open + 1, close, max)
            if (close < open) backtrack(result, "$current)", open, close + 1, max)
        }
    }
}