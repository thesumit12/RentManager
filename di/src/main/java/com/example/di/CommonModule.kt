package com.example.di

import com.example.components.IRouter
import org.koin.dsl.module.module

/**
@file_name: CommonModule.kt
@author: Sumit
@brief: CommonModule class to hold all DI objects which are kind of utilities.
@date: 01/11/2019
 */
internal val commonModule = module {

    @Suppress("UNCHECKED_CAST")
    single { Router() as IRouter }

}