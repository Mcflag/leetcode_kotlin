package com.ccooy.testonly.leet1_20

class Solution6 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var s = "LEETCODEISHIRING"
            var s2 = "AB"
            println(convert1(s2, 1))
        }

        fun convert(s: String, numRows: Int): String {
            if (numRows == 1) return s
            var list = ArrayList<StringBuilder>()
            for (i in 0 until Math.min(numRows, s.length)) {
                list.add(StringBuilder())
            }
            var curRow = 0
            var goingDown = false
            for (c in s) {
                list.get(curRow).append(c)
                if (curRow == 0 || curRow == numRows - 1) goingDown = !goingDown
                curRow += if (goingDown) 1 else -1
            }
            var result = StringBuilder()
            for (r in list) {
                result.append(r)
            }
            return result.toString()
        }

        fun convert1(s: String, numRows: Int): String {
            if (numRows == 1) return s
            var result = StringBuilder()
            var len = s.length
            var cycleLen = 2 * numRows - 2
            for (i in 0 until numRows) {
                for (j in 0 until len - i step cycleLen) {
                    result.append(s[j + i])
                    if (i != 0 && i != numRows - 1 && j + cycleLen - i < len) {
                        result.append(s[j + cycleLen - i])
                    }
                }
            }
            return result.toString()
        }
    }
}