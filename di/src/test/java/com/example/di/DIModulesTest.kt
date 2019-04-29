package com.example.di

import com.example.database.dao.TestDao
import com.example.database.repository.TestRepository
import org.junit.Assert
import org.junit.Test
import org.koin.test.AutoCloseKoinTest
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.get
import org.koin.test.declareMock

/**
 * DI module test cases
 * @author Sumit Lakra
 * @date 04/29/2019
 */
class DIModulesTest : AutoCloseKoinTest() {

    @Test
    fun repository_module_test() {
        startKoin(listOf(databaseModule, repositoryModule))
        val testDao = declareMock<TestDao> { }

        val testRepo = get<TestRepository>()
        Assert.assertNotNull(testRepo)
        Assert.assertTrue(testRepo.testDao == testDao)
    }

    @Test
    fun view_model_module_test() {
        startKoin(appModule)

        declareMock<TestDao> {  }
        //test for viewmodels
    }
}