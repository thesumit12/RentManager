package com.example.database.dao

import androidx.room.*

/**
 * This class provides the basic CRUD operations for Room Database. The Room library generates
 * the implementation for the methods during building.
 * @author: Sumit lakra
 * @date: 04/25/2019
 */
@Dao
abstract class RoomBaseDao<T> {

    /**
     * abstract function to insert entity in the database. Aborts the current statement if constraint violation occurs
     * @author Sumit Lakra
     * @date 04/25/2019
     * @param [item] T
     * @return [Long] row Id of the inserted record
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertEntity(item: T): Long

    /**
     * abstract function to to insert bulk records in DB
     * @author Sumit Lakra
     * @date 04/25/2019
     * @param [items] List<T>
     */
    @Insert(onConflict = OnConflictStrategy.ABORT)
    abstract fun insertAll(items: List<T>)

    /**
     * abstract function to to insert bulk records in DB with replace strategy
     * @author Sumit Lakra
     * @date 04/25/2019
     * @param [items] List<T>
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAllWithReplaceStratedy(items: List<T>)

    /**
     * abstract function to update entity in the database
     * @author Sumit Lakra
     * @date 04/25/2019
     * @param [item] T
     * @return [Int] number of rows affected by update
     */
    @Update
    abstract fun updateEntity(item: T): Int

    /**
     * abstract function to delete entity from the database
     * @author Sumit Lakra
     * @date 04/25/2019
     * @param [item] T
     */
    @Delete
    abstract fun deleteEntity(item: T)
}
