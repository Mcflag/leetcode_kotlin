package com.ccooy.testonly.leet1_20

import java.util.*

class Solution20 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val a = "()[]{}"
            println(isValid(a))
            val b = "([)]"
            println(isValid(b))
        }

        fun isValid1(s: String): Boolean {
            var temp = s
            while (temp.contains("()") || temp.contains("[]") || temp.contains("{}")) {
                temp = temp.replace("()", "")
                temp = temp.replace("[]", "")
                temp = temp.replace("{}", "")
            }
            return temp == ""
        }

        fun isValid2(s: String): Boolean {
            if (s.length % 2 != 0) return false
            val mappings = mutableMapOf(')' to '(', ']' to '[', '}' to '{')
            val stack = Stack<Char>()
            for (c in s) {
                if (mappings.containsKey(c)) {
                    val topElement = if (stack.empty()) '#' else stack.pop()
                    if (topElement != mappings[c]) {
                        return false
                    }
                } else {
                    stack.push(c)
                }
            }
            return stack.isEmpty()
        }

        fun isValid(s: String): Boolean {
            if (s.length % 2 != 0) return false
            val stack = Parentheses()
            for (c in s) {
                if (stack.process(c).not()) return false
            }
            if (stack.size != 0) return false
            return true
        }
    }

    class Parentheses {
        val stack = LinkedList<Char>()

        fun process(p: Char): Boolean {
            return when (p) {
                '(', '[', '{' -> {
                    stack.push(p)
                    true
                }
                ')', ']', '}' -> {
                    popup(p)
                }

                else -> false
            }
        }

        val size: Int
            get() {
                return stack.size
            }

        private fun popup(p: Char): Boolean {
            if (stack.isEmpty()) return false
            val op = stack.pop()
            return when (p) {
                ')' -> op == '('
                ']' -> op == '['
                '}' -> op == '{'
                else -> false
            }
        }
    }
}