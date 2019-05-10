package com.ccooy.testonly.leet1_20

class Solution4 {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            var n1 = intArrayOf(2, 5, 6, 8, 9)
            var n2 = intArrayOf(1, 10, 45)
            println(findMedianSortedArrays3(n1, n2))
        }

        fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
            var n1 = nums1.size
            var n2 = nums2.size
            var i = 0
            var j = 0
            var all = IntArray(n1 + n2)
            var index = 0
            while (i < n1 && j < n2) {
                if (nums1[i] <= nums2[j]) {
                    all[index++] = nums1[i++]
                } else {
                    all[index++] = nums2[j++]
                }
            }
            while (i < n1) all[index++] = nums1[i++]
            while (j < n2) all[index++] = nums2[j++]
            var mid = all.size / 2
            return if (all.size % 2 != 0) all[mid].toDouble() else (all[mid - 1] + all[mid]) / 2.0
        }

        fun findMedianSortedArrays2(nums1: IntArray, nums2: IntArray): Double {
            var n1 = nums1.size
            var n2 = nums2.size
            var sumLen = n1 + n2
            return if (sumLen % 2 != 0) {
                findKthSmallest(nums1, n1, 0, nums2, n2, 0, sumLen / 2 + 1).toDouble()
            } else {
                (findKthSmallest(nums1, n1, 0, nums2, n2, 0, sumLen / 2 + 1) +
                        findKthSmallest(nums1, n1, 0, nums2, n2, 0, sumLen / 2)) / 2.0
            }
        }

        fun findKthSmallest(a: IntArray, m: Int, begin1: Int, b: IntArray, n: Int, begin2: Int, k: Int): Int {
            if (m > n)
                return findKthSmallest(b, n, begin2, a, m, begin1, k)
            if (m == 0)
                return b[begin2 + k - 1]
            if (k == 1)
                return Math.min(a[begin1], b[begin2])

            var partA = Math.min(k / 2, m)
            var partB = k - partA
            return if (a[begin1 + partA - 1] == b[begin2 + partB - 1]) {
                a[begin1 + partA - 1]
            } else if (a[begin1 + partA - 1] < b[begin2 + partB - 1]) {
                findKthSmallest(a, m - partA, begin1 + partA, b, n, begin2, k - partA)
            } else {
                findKthSmallest(a, m, begin1, b, n - partB, begin2 + partB, k - partB)
            }
        }

        fun findMedianSortedArrays3(nums1: IntArray, nums2: IntArray): Double {
            var anums = if (nums1.size > nums2.size) nums2 else nums1
            var bnums = if (nums1.size > nums2.size) nums1 else nums2
            var m = anums.size
            var n = bnums.size
            var iMin = 0
            var iMax = m
            var halfLen = (m + n + 1) / 2
            while (iMin <= iMax) {
                val i = (iMin + iMax) / 2
                val j = halfLen - i
                if (i < iMax && bnums[j - 1] > anums[i]) {
                    iMin = i + 1
                } else if (i > iMin && anums[i - 1] > bnums[j]) {
                    iMax = i - 1
                } else {
                    val maxLeft = when {
                        i == 0 -> bnums[j - 1]
                        j == 0 -> anums[i - 1]
                        else -> Math.max(anums[i - 1], bnums[j - 1])
                    }
                    if ((m + n) % 2 == 1) {
                        return maxLeft.toDouble()
                    }

                    val minRight = when {
                        i == m -> bnums[j]
                        j == n -> anums[i]
                        else -> Math.min(bnums[j], anums[i])
                    }
                    return (maxLeft + minRight) / 2.0
                }
            }
            return 0.0
        }
    }
}