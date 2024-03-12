package com.huar.mvvmlab

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SectionATest {
    @Test
    fun checkMeetingAttend(){
        val test1 = arrayOf(intArrayOf(0, 30), intArrayOf(5, 10), intArrayOf(15, 20))
        val test2 = arrayOf(intArrayOf(7, 10), intArrayOf(2, 4))
        val test3 = arrayOf(intArrayOf(1, 5), intArrayOf(8, 9), intArrayOf(8, 10))

        println("test1: ${checkMeetingsAttend(test1)}")
        println("test2: ${checkMeetingsAttend(test2)}")
        println("test3: ${checkMeetingsAttend(test3)}")
    }
}