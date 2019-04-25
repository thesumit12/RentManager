package com.example.database.repository

import com.example.database.dao.TestDao
import com.example.database.entity.TestEntity

/**
 * Test Repository replace with your own implementation
 * @author Sumit Lakra
 * @date 04/25/2019
 */
class TestRepository (
    val testDao: TestDao
) {

    /**
     * Test function replace with your own implementation
     * @author Sumit Lakra
     * @param [name] String
     * @date 04/25/2019
     */
    fun testDbFunction(name: String): TestEntity = testDao.testFunction(name)
}