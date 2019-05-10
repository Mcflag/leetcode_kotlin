package com.ccooy.testonly.leet1_20

class Solution05 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var s = "bbaaaaaaccccaaaadddd"
            println(longestPalindrome4(s))
        }

        fun isPalindromic(s: String): Boolean {
            var len = s.length
            for (i in 0 until len / 2) {
                if (s[i] != s[len - i - 1]) {
                    return false
                }
            }
            return true
        }

        fun longestPalindrome(s: String): String {
            var ans = ""
            var max = 0
            var len = s.length
            for (i in 0 until len) {
                for (j in i + 1..len) {
                    var str = s.substring(i, j)
                    if (isPalindromic(str) && str.length > max) {
                        ans = str
                        max = Math.max(max, ans.length)
                    }
                }
            }
            return ans
        }

        fun longestPalindrome1(s: String): String {
            if (s.equals("")) return ""
            var reverse = s.reversed()
            var len = s.length
            var arr = Array(len) { IntArray(len) }
            var maxLen = 0
            var maxEnd = 0
            for (i in 0 until len) {
                for (j in 0 until len) {
                    if (s[i] == reverse[j]) {
                        if (i == 0 || j == 0) {
                            arr[i][j] = 1
                        } else {
                            arr[i][j] = arr[i - 1][j - 1] + 1
                        }
                    }
                    if (arr[i][j] > maxLen) {
                        var before = len - 1 - j
                        if (before + arr[i][j] - 1 == i) {
                            maxLen = arr[i][j]
                            maxEnd = i
                        }
                    }
                }
            }
            return s.substring(maxEnd - maxLen + 1, maxEnd + 1)
        }

        fun longestPalindrome2(s: String): String {
            if (s.equals("")) return ""
            var reverse = s.reversed()
            var len = s.length
            var arr = IntArray(len)
            var maxLen = 0
            var maxEnd = 0
            for (i in 0 until len) {
                for (j in (len - 1) downTo 0) {
                    if (s[i] == reverse[j]) {
                        if (i == 0 || j == 0) {
                            arr[j] = 1
                        } else {
                            arr[j] = arr[j - 1] + 1
                        }
                    } else {
                        arr[j] = 0
                    }
                    if (arr[j] > maxLen) {
                        var before = len - 1 - j
                        if (before + arr[j] - 1 == i) {
                            maxLen = arr[j]
                            maxEnd = i
                        }
                    }
                }
            }
            return s.substring(maxEnd - maxLen + 1, maxEnd + 1)
        }

        fun longestPalindrome3(s: String): String {
            if (s.isEmpty()) return ""
            var start = 0
            var end = 0
            for (i in 0 until s.length) {
                var len1 = expandAroundCenter(s, i, i)
                var len2 = expandAroundCenter(s, i, i + 1)
                var len = Math.max(len1, len2)
                if (len > end - start) {
                    start = i - (len - 1) / 2
                    end = i + len / 2
                }
            }
            return s.substring(start, end + 1)
        }

        fun expandAroundCenter(s: String, left: Int, right: Int): Int {
            var l = left
            var r = right
            while (l >= 0 && r < s.length && s[l] == s[r]) {
                l--
                r++
            }
            return r - l - 1
        }

        fun preProcess(s: String): String {
            if (s.length == 0) {
                return "^$"
            }
            var result = "^"
            for (a in s) {
                result += "#$a"
            }
            result += "#$"
            return result
        }

        fun longestPalindrome4(s: String): String {
            var str = preProcess(s)
            var n = str.length
            var p = IntArray(n)
            var c = 0
            var r = 0
            for (i in 1 until n - 1) {
                val i_mirror = 2 * c - i
                if (r > i) {
                    p[i] = Math.min(r - i, p[i_mirror]) //防止超出r
                } else {
                    p[i] = 0 //r=i的情况
                }
                while (str[i + 1 + p[i]] == str[i - 1 - p[i]]) {
                    p[i]++
                }
                if (i + p[i] > r) {
                    c = i
                    r = i + p[i]
                }
            }
            var maxLen = 0
            var centerIndex = 0
            for (i in 1 until n - 1) {
                if (p[i] > maxLen) {
                    maxLen = p[i]
                    centerIndex = i
                }
            }
            val start = (centerIndex - maxLen) / 2
            return s.substring(start, start + maxLen)
        }
    }
}