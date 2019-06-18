package com.example.components.common

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {

    fun getCurrentMonth(): String {
        val dateFormat = SimpleDateFormat("MMM yyyy", Locale.getDefault())
        return dateFormat.format(Calendar.getInstance().time)
    }

    fun getCurrentTimeStamp(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.getDefault())
        return dateFormat.format(Calendar.getInstance().time)
    }

    fun getCurrentDate(): String {
        val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
        return dateFormat.format(Calendar.getInstance().time)
    }
}