package com.ccooy.testonly.leet21_40

import java.util.*

class Solution32 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var s = ")()())"
            println(longestValidParentheses(s))
        }

        fun longestValidParentheses1(s: String): Int {
            var left = 0
            var right = 0
            var max = 0
            for (c in s) {
                if (c == '(') left++ else right++
                if (left == right) {
                    max = Math.max(max, 2 * right)
                } else if (right > left) {
                    left = 0
                    right = 0
                }
            }
            left = 0
            right = 0
            for (i in s.length - 1 downTo 0) {
                if (s[i] == '(') left++ else right++
                if (left == right) {
                    max = Math.max(max, 2 * left)
                } else if (left > right) {
                    left = 0
                    right = 0
                }
            }
            return max
        }

        fun longestValidParentheses2(s: String): Int {
            var max = 0
            var numStack = Stack<Int>()
            numStack.push(-1)
            for (i in 0 until s.length) {
                if (s[i] == '(') {
                    numStack.push(i)
                } else {
                    numStack.pop()
                    if (numStack.empty()) {
                        numStack.push(i)
                    } else {
                        max = Math.max(max, i - numStack.peek())
                    }
                }
            }
            return max
        }

        fun longestValidParentheses(s: String): Int {
            var max = 0
            var dp = Array(s.length) { 0 }
            for (i in 1 until s.length) {
                if (s[i] == ')') {
                    if (s[i - 1] == '(') {
                        dp[i] = (if (i >= 2) dp[i - 2] else 0) + 2
                    } else if (i - dp[i - 1] > 0 && s[i - dp[i - 1] - 1] == '(') {
                        dp[i] = dp[i - 1] + (if (i - dp[i - 1] >= 2) dp[i - dp[i - 1] - 2] else 0) + 2
                    }
                    max = Math.max(max, dp[i])
                }
            }
            return max
        }
    }
}