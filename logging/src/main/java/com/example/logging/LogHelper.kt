package com.example.logging

import timber.log.Timber

/**
Interface for mocking logger
@author sbhushan
@date 01/29/2018
 */
interface ILogger {
    /**
     * to print messages for debugging purposes
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    fun setupTimberLogging()
    /**
     * to print messages for debugging purposes
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    fun d(msg: String)
    /**
     * to print useful information on logcat
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    fun i(msg: String)
    /**
     * to log warnings
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    fun w(msg: String)
    /**
     * to log error messages
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    fun e(msg: String)
    /**
     * to log exceptions messages
     * @param [e] Exception
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    fun ex(e: Throwable)
}

/**
 * Utility class to add log
 */
object LogHelper : ILogger {
    /**
     * to print messages for debugging purposes
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    override fun setupTimberLogging() = Timber.plant(FileLoggingTree())
    /**
     * to print messages for debugging purposes
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    override fun d(msg: String) {
        log(LogType.DEBUG, msg)
    }

    /**
     * to print useful information on logcat
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    override fun i(msg: String) {
        log(LogType.INFO, msg)
    }

    /**
     * to log warnings
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    override fun w(msg: String) {
        log(LogType.WARN, msg)
    }

    /**
     * to log error messages
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    override fun e(msg: String) {
        log(LogType.ERROR, msg)
    }

    /**
     * to log exceptions messages
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    @Suppress("FunctionMinLength")
    override fun ex(e: Throwable) {
        Timber.e(e)
    }

    private fun log(type: LogType, msg: String) {
        when (type) {
            LogType.DEBUG -> Timber.d(msg)
            LogType.INFO -> Timber.i(msg)
            LogType.WARN -> Timber.w(msg)
            LogType.ERROR -> Timber.e(msg)
        }
    }

    /**
     * class containing enums for log types
     * @author Sumit Lakra
     * @date 04/26/2019
     */
    enum class LogType {
        DEBUG,
        INFO,
        WARN,
        ERROR
    }
}
