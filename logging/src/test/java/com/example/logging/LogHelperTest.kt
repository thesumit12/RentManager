package com.example.logging

import com.nhaarman.mockito_kotlin.any
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.powermock.api.mockito.PowerMockito
import org.powermock.core.classloader.annotations.PrepareForTest
import org.powermock.modules.junit4.PowerMockRunner
import timber.log.Timber

/**
 * Class for testing the loghelper file
 * @author pkumar
 * @date 03/07/2019
 */

@RunWith(PowerMockRunner::class)
@PrepareForTest(Timber::class)
class LogHelperTest {

    @Test
    fun logsTest() {
        var answer = ""
        var plantTree = ""

        PowerMockito.mockStatic(Timber::class.java)
        PowerMockito.`when`(Timber.d("This is Debug Message")).thenAnswer {
            answer = "DEBUG"
            answer
        }

        PowerMockito.`when`(Timber.i("This is Info Message")).thenAnswer {
            answer = "INFO"
            answer
        }

        PowerMockito.`when`(Timber.w("This is Warning Message")).thenAnswer {
            answer = "WARNING"
            answer
        }

        PowerMockito.`when`(Timber.e("This is Error Message")).thenAnswer {
            answer = "ERROR"
            answer
        }

        PowerMockito.`when`(Timber.plant(any())).thenAnswer {
            plantTree = "TREE_PLANTED"
            plantTree
        }

        LogHelper.d("This is Debug Message")
        assertTrue(answer == "DEBUG")

        LogHelper.i("This is Info Message")
        assertTrue(answer == "INFO")

        LogHelper.w("This is Warning Message")
        assertTrue(answer == "WARNING")

        LogHelper.e("This is Error Message")
        assertTrue(answer == "ERROR")

        val throwable = Throwable()
        LogHelper.ex(throwable)
        assertTrue(answer == "ERROR")

        LogHelper.setupTimberLogging()
        assertTrue(plantTree == "TREE_PLANTED")
    }
}