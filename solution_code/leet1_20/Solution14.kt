package com.ccooy.testonly.leet1_20

class Solution14 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val arr = arrayOf("flower", "flow", "flight")
            println(longestCommonPrefix(arr))
            val arr1 = arrayOf("dog", "racecar", "car")
            println(longestCommonPrefix(arr1))
        }

        fun longestCommonPrefix2(strs: Array<String>): String {
            return if (strs.isEmpty()) "" else strs.reduce { a, b -> a.commonPrefixWith(b) }
        }

        fun longestCommonPrefix1(strs: Array<String>): String {
            if (strs.isEmpty()) return ""
            for (i in 0 until strs[0].length) {
                val c = strs[0][i]
                for (j in 1 until strs.size) {
                    if (i == strs[j].length || strs[j][i] != c)
                        return strs[0].substring(0, i)
                }
            }
            return strs[0]
        }

        fun longestCommonPrefix3(strs: Array<String>): String {
            if (strs.isEmpty()) return ""
            if (strs.size == 1) return strs.first()
            val minLen = strs.map { it.length }.min()!!

            var low = 1
            var high = minLen

            while (low <= high) {
                val middle = (low + high) / 2

                if (isCommonPrefix(strs, middle)) {
                    low = middle + 1
                } else {
                    high = middle - 1
                }
            }
            return strs.first().substring(0, (low + high) / 2)
        }

        fun isCommonPrefix(strs: Array<String>, prefixLen: Int): Boolean {
            if (prefixLen == 0) return false
            val prefix = strs.first().substring(0, prefixLen)
            for (index in 1..(strs.size - 1)) {
                if (!strs[index].startsWith(prefix)) {
                    return false
                }
            }

            return true
        }

        fun longestCommonPrefix(strs: Array<String>): String {
            if (strs.isEmpty()) return ""
            if (strs.size == 1) return strs[0]
            val trie = Trie()
            for (i in 1 until strs.size) {
                trie.insert(strs[i])
            }
            return trie.searchLongestPrefix(strs[0])
        }
    }

    class TrieNode {

        private val links: Array<TrieNode?>

        private val r = 26
        var size = 0

        var isEnd: Boolean = false

        init {
            links = arrayOfNulls(r)
        }

        fun containsKey(ch: Char): Boolean {
            return links[ch - 'a'] != null
        }

        fun getLink(ch: Char): TrieNode? {
            return links[ch - 'a']
        }

        fun putLink(ch: Char, node: TrieNode) {
            links[ch - 'a'] = node
            size++
        }

        fun setEnd() {
            isEnd = true
        }
    }

    class Trie {
        private val root: TrieNode = TrieNode()

        // Inserts a word into the trie.
        fun insert(word: String) {
            var node = root
            for (i in 0 until word.length) {
                val currentChar = word[i]
                if (!node.containsKey(currentChar)) {
                    node.putLink(currentChar, TrieNode())
                }
                node = node.getLink(currentChar)!!
            }
            node.setEnd()
        }

        fun searchPrefix(word: String): TrieNode? {
            var node = root
            for (i in 0 until word.length) {
                val curLetter = word[i]
                if (node.containsKey(curLetter)) {
                    node = node.getLink(curLetter)!!
                } else {
                    return null
                }
            }
            return node
        }

        fun searchLongestPrefix(word: String): String {
            var node = root
            val prefix = StringBuilder()
            for (i in 0 until word.length) {
                val curLetter = word[i]
                if (node.containsKey(curLetter) && node.size == 1 && !node.isEnd) {
                    prefix.append(curLetter)
                    node = node.getLink(curLetter)!!
                } else
                    return prefix.toString()

            }
            return prefix.toString()
        }

        fun search(word: String): Boolean {
            val node = searchPrefix(word)
            return node != null && node.isEnd
        }

        fun startsWith(prefix: String): Boolean {
            val node = searchPrefix(prefix)
            return node != null
        }
    }

}