package com.ccooy.testonly.leet21_40

class Solution30 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var s = "abababab"
            var words = arrayOf("ab", "ba")
            println(findSubstring(s, words))
        }

        fun findSubstring1(s: String, words: Array<String>): List<Int> {
            var res = ArrayList<Int>()
            if (words.isEmpty()) return res
            var wordLen = words[0].length
            var allWords: HashMap<String, Int> = HashMap()
            for (w in words) {
                var v = allWords[w] ?: 0
                allWords[w] = v + 1
            }
            for (i in 0 until s.length - words.size * wordLen + 1) {
                var hasWords: HashMap<String, Int> = HashMap()
                var num = 0
                while (num < words.size) {
                    var word = s.substring(i + num * wordLen, i + (num + 1) * wordLen)
                    if (allWords.containsKey(word)) {
                        var value = hasWords[word] ?: 0
                        hasWords[word] = value + 1
                        if ((hasWords[word] ?: 0) > (allWords[word] ?: 0)) break
                    } else {
                        break
                    }
                    num++
                }
                if (num == words.size) {
                    res.add(i)
                }
            }
            return res
        }

        fun findSubstring(s: String, words: Array<String>): List<Int> {
            var res = ArrayList<Int>()
            if (words.isEmpty()) return res
            var wordLen = words[0].length
            var allWords: HashMap<String, Int> = HashMap()
            for (w in words) {
                var v = allWords[w] ?: 0
                allWords[w] = v + 1
            }
            for (j in 0 until wordLen) {
                var hasWords: HashMap<String, Int> = HashMap()
                var num = 0
                var i = j
                while (i < s.length - words.size * wordLen + 1) {
                    var hasRemoved = false
                    while (num < words.size) {
                        var word = s.substring(i + num * wordLen, i + (num + 1) * wordLen)
                        if (allWords.containsKey(word)) {
                            var value = hasWords[word] ?: 0
                            hasWords[word] = value + 1
                            if ((hasWords[word] ?: 0) > (allWords[word] ?: 0)) {
                                hasRemoved = true
                                var removeNum = 0
                                while ((hasWords[word] ?: 0) > (allWords[word] ?: 0)) {
                                    val firstWord = s.substring(i + removeNum * wordLen, i + (removeNum + 1) * wordLen)
                                    val v = hasWords[firstWord] ?: 0
                                    hasWords[firstWord] = v - 1
                                    removeNum++
                                }
                                num = num - removeNum + 1
                                i += (removeNum - 1) * wordLen
                                break
                            }
                        } else {
                            hasWords.clear()
                            i += (num * wordLen)
                            num = 0
                            break
                        }
                        num++
                    }
                    if (num == words.size) {
                        res.add(i)
                    }
                    if (num > 0 && !hasRemoved) {
                        val firstWord = s.substring(i, i + wordLen)
                        val v = hasWords[firstWord] ?: 0
                        hasWords[firstWord] = v - 1
                        num -= 1
                    }
                    i += wordLen
                }
            }
            return res
        }
    }
}