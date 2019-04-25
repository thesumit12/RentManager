package com.example.components.common

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.components.R

/**
 * @brief: Helper class to show waiting dialog box in the app
 * @author: Sumit Lakra
 * @date: 04/25/19
 */
class WaitingDialogHelper {

    private var dlg: AlertDialog? = null
    private val isShowing: Boolean
        get() = dlg?.isShowing == true

    /**
     * Function to show dialog
     * @param [context] Context
     * @param [title] Int
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    fun show(context: Context, title: Int) {
        hideDialog()
        val body = LayoutInflater.from(context).inflate(R.layout.waiting_large, null, false)
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setView(body)
            .setCancelable(true)
        dlg = builder.show()
    }

    /**
     * Function to show dialog
     * @param [context] Context
     * @param [title] String
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    fun show(context: Context, title: String) {
        hideDialog()
        val body = LayoutInflater.from(context).inflate(R.layout.waiting_large, null, false)
        val builder = AlertDialog.Builder(context)
            .setTitle(title)
            .setView(body)
            .setCancelable(true)
        dlg = builder.show()
    }

    /**
     * Function to hide dialog
     * @param [context] Context
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    fun hideDialog() {
        if (isShowing) {
            try {
                dlg?.dismiss()
            } catch (ignore: Exception) {
            }

            dlg = null
        }
    }
}