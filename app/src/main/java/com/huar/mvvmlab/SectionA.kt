package com.huar.mvvmlab

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), please provide a function with implementation by Kotlin to determine if a person could attend all meetings.
 * For example,
 * Input: intervals = [[0,30], [5,10], [15,20]] Output: false
 * Explanation: The person can only attend some meetings because there is an overlap between [0,30] and [5,10], and between [0,30] and [15,20].
 * Input: intervals = [[7,10], [2,4]] Output: true
 * Explanation: The person can attend all meetings because there is no overlap between [7,10] and [2,4].
 */

fun checkMeetingsAttend(intervals: Array<IntArray>): Boolean {
    intervals.sortBy { it[0] }

    for (i in 0 until intervals.size - 1) {
        if (intervals[i][1] > intervals[i + 1][0]) {
            return false
        }
    }
    return true
}

//Test
fun main() {
    val test1 = arrayOf(intArrayOf(0, 30), intArrayOf(5, 10), intArrayOf(15, 20))
    val test2 = arrayOf(intArrayOf(7, 10), intArrayOf(2, 4))
    val test3 = arrayOf(intArrayOf(1, 5), intArrayOf(8, 9), intArrayOf(8, 10))

    println("test1: ${checkMeetingsAttend(test1)}")
    println("test2: ${checkMeetingsAttend(test2)}")
    println("test3: ${checkMeetingsAttend(test3)}")
}
