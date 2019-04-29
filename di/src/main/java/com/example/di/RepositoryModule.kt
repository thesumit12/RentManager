package com.example.di

import com.example.database.repository.TestRepository
import org.koin.dsl.module.module

/**
 * Module will initialize all repositories
 * @author Sumit Lakra
 * @date 04/29/19
 */
internal val repositoryModule = module {

    //Test Repository
    single { TestRepository(testDao = get()) }
}