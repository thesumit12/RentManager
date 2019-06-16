package com.example.rentmanager

import android.app.Application
import com.example.di.appModule
import com.example.logging.LogHelper
import com.github.anrwatchdog.ANRWatchDog
import com.squareup.leakcanary.LeakCanary
import org.koin.android.ext.android.startKoin

/**
@file_name: RentManagerApp.kt
@author: Sumit Lakra
@brief: Android Application class for core context.
@date: 04/29/2019
 */
class RentManagerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        LogHelper.e("Inside Application")
        LogHelper.setupTimberLogging()
        initKoin()
        initLeakCanary()
        initANRWatchDog()
    }

    /**
     * Will start kotlin-koin
     * @author Sumit Lakra
     * @date 01/09/19
     */
    private fun initKoin() {
        startKoin(this, appModule)
    }

    /**
     * Function to configure LeakCanary.
     */
    private fun initLeakCanary() {
        if (LeakCanary.isInAnalyzerProcess(this)) {
            //  This process is dedicated to LeakCanary for heap analysis.
            //  You should not init your app in this process.
            return
        }
        LeakCanary.install(this)
    }

    /**
     * Function to configure ANRWatchDog.
     */
    private fun initANRWatchDog() = ANRWatchDog().start()
}