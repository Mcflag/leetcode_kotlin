package com.ccooy.testonly.leet21_40

class Solution28 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var h = "h"
            var n = "he"
            println(strStr(h, n))
        }

        fun strStr1(haystack: String, needle: String): Int {
            if (needle.isEmpty()) return 0
            var i = 0
            var j = 0
            while (i != haystack.length && j != needle.length) {
                if (haystack[i] == needle[j]) {
                    i++
                    j++
                } else {
                    i = i - j + 1
                    j = 0
                }
            }
            return if (j == needle.length) i - j else -1
        }

        fun strStr2(haystack: String, needle: String): Int {
            if (needle.isEmpty()) return 0
            var next = getNext(needle)
            var i = 0
            var j = 0
            while (i < haystack.length && j < needle.length) {
                if (j == -1 || haystack[i] == needle[j]) {
                    i++
                    j++
                } else {
                    j = next[j]
                }
            }
            return if (j == needle.length) i - j else -1
        }

        fun getNext(needle: String): Array<Int> {
            var next = Array(needle.length) { 0 }
            next[0] = -1
            var k = -1
            var j = 0
            while (j < needle.length - 1) {
                if (k == -1 || needle[j] == needle[k]) {
                    k++
                    j++
                    if (needle[j] != needle[k]) {
                        next[j] = k
                    } else {
                        next[j] = next[k]
                    }
                } else {
                    k = next[k]
                }
            }
            return next
        }

        fun strStr3(haystack: String, needle: String): Int {
            if (needle.isEmpty()) return 0
            var right = Array(256) { -1 }
            for ((i, c) in needle.withIndex()) {
                right[c.toInt()] = i
            }
            var skip = 0
            var i = 0
            while (i <= haystack.length - needle.length) {
                skip = 0
                for (j in needle.length - 1 downTo 0) {
                    if (needle[j] != haystack[i + j]) {
                        skip = j - right[haystack[i + j].toInt()]
                        if (skip < 1) skip = 1
                        break
                    }
                }
                if (skip == 0) return i
                i += skip
            }
            return -1
        }

        fun strStr(haystack: String, needle: String): Int {
            if (needle.isEmpty()) return 0
            var moveLength = Array(256) { needle.length + 1 }
            for ((a, c) in needle.withIndex()) {
                moveLength[c.toInt()] = needle.length - a
            }
            var i = 0
            while (i < haystack.length) {
                var j = 0
                while (j < needle.length && i + j < haystack.length && haystack[i + j] == needle[j]) {
                    j++
                }

                if (j >= needle.length) return i
                if (i + needle.length > haystack.length-1) return -1
                i += moveLength[haystack[i + needle.length].toInt()]
            }
            return -1
        }
    }
}