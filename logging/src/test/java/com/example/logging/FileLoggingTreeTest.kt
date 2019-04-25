package com.example.logging

import android.util.Log
import com.nhaarman.mockito_kotlin.any
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
@PrepareForTest(Log::class)
class FileLoggingTreeTest {

    @Test
    fun showLogsOnTerminalTest() {
        var ret = 0
        PowerMockito.mockStatic(Log::class.java)

        PowerMockito.`when`(Log.d(any(), any())).then {
            ret = 3
            ret
        }
        PowerMockito.`when`(Log.e(any(), any())).then {
            ret = 6
            ret
        }
        PowerMockito.`when`(Log.w(Mockito.anyString(), Mockito.anyString())).then {
            ret = 5
            ret
        }
        PowerMockito.`when`(Log.i(any(), any())).then {
            ret = 4
            ret
        }
        PowerMockito.`when`(Log.v(any(), any())).then {
            ret = 2
            ret
        }

        val fileLoggingTree = FileLoggingTree()

        fileLoggingTree.showLogsOnTerminal("TimeStamp", "TAG", 3, "MESSAGE")
        assertTrue(ret == 3)

        fileLoggingTree.showLogsOnTerminal("timeStamp", "TAG", 2, "MESSAGE")
        assertTrue(ret == 2)

        fileLoggingTree.showLogsOnTerminal("timeStamp", "TAG", 4, "MESSAGE")
        assertTrue(ret == 4)

        fileLoggingTree.showLogsOnTerminal("timeStamp", "TAG", 5, "MESSAGE")
        assertTrue(ret == 5)

        fileLoggingTree.showLogsOnTerminal("timeStamp", "TAG", 6, "MESSAGE")
        assertTrue(ret == 6)

        fileLoggingTree.showLogsOnTerminal("timeStamp", "TAG", 1, "MESSAGE")
        assertTrue(ret == 6)
    }

    @Test
    fun getPriorityStringTest() {
        val config = Config
        val fileLoggingTree = FileLoggingTree()

        var ret = fileLoggingTree.priorityString(2)
        assertTrue(ret == config.VERBOSE)

        ret = fileLoggingTree.priorityString(3)
        assertTrue(ret == config.DEBUG)

        ret = fileLoggingTree.priorityString(4)
        assertTrue(ret == config.INFO)

        ret = fileLoggingTree.priorityString(5)
        assertTrue(ret == config.WARN)

        ret = fileLoggingTree.priorityString(6)
        assertTrue(ret == config.ERROR)

        ret = fileLoggingTree.priorityString(1)
        assertTrue(ret == config.ERROR)
    }
}