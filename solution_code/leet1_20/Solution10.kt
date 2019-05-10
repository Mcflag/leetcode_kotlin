package com.ccooy.testonly.leet1_20

class Solution10 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            test("", "a", false)
            test("a", "", false)
            test("", "", true)
            test("", ".", false)
            test("aaaa", ".*", true)
            test("aa", "a", false)
            test("aa", "a*", true)
            test("ab", ".*", true)
            test("aab", "c*a*b", true)
            test("mississippi", "mis*is*p*.", false)
            test("aaa", "ab*ac*a", true)
            test("a", ".", false)

            isMatch1("aaa", "ab*ac*a")
        }

        fun test(s: String, p: String, b: Boolean) {
            if (isMatch(s, p) != b) {
                println("$s and $p -> ${isMatch(s, p)}")
            }
        }

        fun isMatch(s: String, p: String): Boolean {
            if (p.isEmpty()) return s.isEmpty()
            var b = !s.isEmpty() && (p[0] == s[0] || p[0] == '.')
            if (p.length >= 2 && p[1] == '*') {
                return isMatch(s, p.substring(2)) || (b && isMatch(s.substring(1), p))
            } else {
                return b && isMatch(s.substring(1), p.substring(1))
            }
        }

        fun isMatch1(s: String, p: String): Boolean {
            val dp = Array(2) { BooleanArray(p.length + 1) }
            dp[s.length % 2][p.length] = true
            for (i in s.length downTo 0) {
                for (j in p.length downTo 0) {
                    if (i == s.length && j == p.length) continue
                    var first_match = i < s.length && j < p.length && (s[i] == p[j] || p[j] == '.')
                    if (j + 1 < p.length && p[j + 1] == '*') {
                        dp[i % 2][j] = dp[i % 2][j + 2] || (first_match && dp[(i + 1) % 2][j])
                    } else {
                        dp[i % 2][j] = first_match && dp[(i + 1) % 2][j + 1]
                    }
                }
            }
            return dp[0][0]
        }
    }
}