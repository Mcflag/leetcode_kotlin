package com.ccooy.testonly.leet1_20

class Solution12 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var a = 58
            println(intToRoman(a)) //ans:LVIII
            var b = 1994
            println(intToRoman(b)) //ans:MCMXCIV
        }

        fun intToRoman(num: Int): String {
            var m = arrayOf("", "M", "MM", "MMM")
            var c = arrayOf("", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM")
            var x = arrayOf("", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC")
            var i = arrayOf("", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX")
            return m[num / 1000] + c[(num % 1000) / 100] + x[(num % 100) / 10] + i[num % 10]
        }
    }
}