package com.example.logging

import android.annotation.SuppressLint
import android.os.Environment
import android.util.Log
import timber.log.Timber
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Brief: This is a class for Logging using Timber . This provides methods to save logs to a file and
 * show them on terminal as well. This class provides a custom implementation of Timber Debug Tree's
 * log method.
 * @author Sumit Lakra
 * @date 04/26/19
 */
class FileLoggingTree : Timber.DebugTree() {

    val fileNameTag = FileLoggingTree::class.java.simpleName

    /**
     * @Author: Sumit Lakra
     * @Function: log
     * @Brief: save logs to file and show on terminal
     * @Parameters: Int - log priority, String - tag, String - log message , t - Throwable
     * @Return: Unit
     * @Date: 04/26/2019
     * @History
     * */
    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {

        try {
            val file = createLogFile()

            val logTimeStamp = SimpleDateFormat("E MMM dd yyyy 'at' hh:mm:ss:SSS aaa",
                Locale.getDefault()).format(Date())
            val priorityString: String = priorityString(priority)

            writeLogsToFile(file, logTimeStamp, tag, priorityString, message)
            showLogsOnTerminal(logTimeStamp, tag, priority, message)
        } catch (e: FileNotFoundException) {
            Log.e(fileNameTag, "File not Found Exception : $e")
        } catch (e: IOException) {
            Log.e(fileNameTag, "IO Exception : $e")
        } catch (e: IllegalArgumentException) {
            Log.e(fileNameTag, "permission error : $e")
        }
    }

    /**
     * @Author: Sumit Lakra
     * @Function: showLogsOnTerminal
     * @Brief: show logs on terminal
     * @Parameters: String - Log time stamp, String - tag, Int - Log priority String - log message
     * @Return: Unit
     * @Date: 04/26/2019
     * @History
     * */
    @SuppressLint("LogNotTimber")
    fun showLogsOnTerminal(logTimeStamp: String, tag: String?, priority: Int, message: String) {
        if (BuildConfig.DEBUG) {
            val msgStr = "$logTimeStamp - $message"
            when (priority) {
                Log.VERBOSE -> Log.v(tag, msgStr)
                Log.DEBUG -> Log.d(tag, msgStr)
                Log.INFO -> Log.i(tag, msgStr)
                Log.WARN -> Log.w(tag, msgStr)
                Log.ERROR -> Log.e(tag, msgStr)
                else -> Log.e(tag, msgStr)
            }
        }
    }

    /**
     * @Author: Sumit Lakra
     * @Function: createLogFile
     * @Brief: Create a file for each date
     * @Parameters: Void
     * @Return: File
     * @Date: 04/26/2019
     * @History
     * */
    @SuppressLint("LogNotTimber")
    fun createLogFile(): File {
        val separator = File.separator
        val logFolderName = Config.LOG_FOLDER_NAME
        val folder = Environment.getExternalStorageDirectory()

        val direct = File(folder.absolutePath, logFolderName)

        if (!direct.exists()) {
            if (direct.mkdirs())
                Log.d(fileNameTag, "no error")
            else
                Log.d(fileNameTag, "error error")
        }

        val fileNameTimeStamp = SimpleDateFormat("dd-MM-yyyy",
            Locale.getDefault()).format(Date())

        val fileName = fileNameTimeStamp + ".log"
        val file = File("$direct$separator$fileName")

        file.createNewFile()
        return file
    }

    /**
     * @Author: Sumit Lakra
     * @Function: writeLogsToFile
     * @Brief: write log to file create above
     * @Parameters: File -  file to save logs to, String - Log time stamp, String - tag,
     * String - Log priority String - log message
     * @Return: Unit
     * @Date: 04/26/2019
     * @History
     * */
    fun writeLogsToFile(
        file: File,
        logTimeStamp: String,
        tag: String?,
        priorityString: String,
        message: String
    ) {
        if (file.exists()) {

            val fileOutputStream = FileOutputStream(file, true)
            val formattedMessage = "$priorityString: $logTimeStamp: $tag: $message\n"
            fileOutputStream.write(formattedMessage.toByteArray())
            fileOutputStream.close()
        }
    }

    /**
     * @Author: Sumit Lakra
     * @Function: priorityString
     * @Brief: get String message from log priority
     * @Parameters: Int - priority
     * @Return: String - Log Priority message
     * @Date: 04/26/2019
     * @History
     * */
    fun priorityString(priority: Int): String = when (priority) {
        Log.VERBOSE -> Config.VERBOSE
        Log.DEBUG -> Config.DEBUG
        Log.INFO -> Config.INFO
        Log.WARN -> Config.WARN
        Log.ERROR -> Config.ERROR
        else -> Config.ERROR
    }
}