package com.example.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @brief: Test Entity class replace with your implementation
 * @author: Sumit Lakra
 * @date: 04/25/19
 */
@Entity(tableName = "table_name")
class TestEntity {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0

    @ColumnInfo(name = "name")
    var name: String = ""
}