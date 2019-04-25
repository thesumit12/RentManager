package com.example.components.common

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager

/**
 * @brief: Utility class for Android related functions
 * @author: Sumit Lakra
 * @date: 04/25/19
 */
object AndroidUtil {

    /**
     * Will show device keyboard.
     * @author Sumit lakra
     * @param [context] Context
     * @param [view] View
     * @date 04/25/19
     */
    fun showKeyboard(context: Context, view: View) {
        @Suppress("UNCHECKED_CAST")
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, 0)
    }

    /**
     * Will hide device keyboard.
     * @author Sumit Lakra
     * @param [context] Activity
     * @date 04/25/19
     */
    fun hideKeyboard(context: Activity) {
        @Suppress("UNCHECKED_CAST")
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (imm.isActive && context.currentFocus != null) {
            imm.hideSoftInputFromWindow(context.currentFocus?.windowToken, 0)
        }
    }
}