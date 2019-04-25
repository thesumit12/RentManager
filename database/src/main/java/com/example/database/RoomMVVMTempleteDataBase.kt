package com.example.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.database.dao.TestDao
import com.example.database.entity.TestEntity

/**
 * This class represents the RoomDatabase class from android. It contains abstract methods to
 * to get the Dao objects corresponding to each table . see DataBaseModule class for getting the instance
 * of this class.
 * @brief: Replace with your own implementation
 * @author Sumit Lakra
 * @date: 04/25/2019
 */
@Database(
    entities = [
    TestEntity::class
    ], version = 1, exportSchema = false
)
abstract class RoomMVVMTempleteDataBase:RoomDatabase() {

    /** @brief: Replace with your own implementation
     * returns TestDao instance
     * @Date: 04/25/2019
     * @author: Sumit Lakra
     * @Return: TestDao
     * */
    abstract fun testDao(): TestDao
}