package com.example.di

import androidx.room.Room
import com.example.database.RoomMVVMTempleteDataBase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module.module

/**
 * Module will initialize database and all dao objects
 * @author Sumit Lakra
 * @date 04/29/19
 */
internal val databaseModule = module {
    //Room Database
    single {
        Room.databaseBuilder(androidApplication(), RoomMVVMTempleteDataBase::class.java, "test-db")
            .allowMainThreadQueries().build()
    }

    //Expose Test dao
    single { get<RoomMVVMTempleteDataBase>().testDao() }
}