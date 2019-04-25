package com.example.database.dao

import androidx.room.Query
import com.example.database.entity.TestEntity

/**
 * @brief: Test Dao class replace with your implementation
 * @author: Sumit Lakra
 * @date: 04/25/2019
 */
abstract class TestDao: RoomBaseDao<TestEntity>() {

    /**
     * @brief: Test function replace with your implementation
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    @Query("SELECT id, name FROM table_name WHERE name = :name " +
            "ORDER BY triggered_on DESC LIMIT 1")
    abstract fun testFunction(name: String): TestEntity
}