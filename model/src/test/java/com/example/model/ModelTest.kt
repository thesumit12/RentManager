package com.example.model

import org.junit.Assert
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * @brief: Test class for all model classes
 * @author: Sumit Lakra
 * @date: 04/26/2019
 */
class ModelTest {

    @Test
    fun testModelTest() {
        val testModel = TestModel()
        testModel.id = 1L
        testModel.name = "sumit"
        testModel.age = 26

        assertTrue(testModel.id == 1L)
        assertTrue(testModel.name == "sumit")
        assertTrue(testModel.age == 26)
    }
}