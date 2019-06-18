package com.example.components.common

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

/**
 * @brief: Helper class to show messages in the app
 * @author: Sumit Lakra
 * @date: 04/25/19
 */
class Notif {

    interface OnButtonClickListener {
        fun onButtonClick(id: Int)
    }

    companion object {

        /**
         * Function to show toast
         * @param [context] Context
         * @param [msg] Int
         * @author: Sumit Lakra
         * @date: 04/25/2019
         */
        fun toast(context: Context?, msg: Int) {
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        /**
         * Function to show toast
         * @param [context] Context
         * @param [msg] String
         * @author: Sumit Lakra
         * @date: 04/25/2019
         */
        fun toast(context: Context?, msg: String){
            Toast.makeText(context, msg, Toast.LENGTH_LONG).show()
        }

        /**
         * Function to show Alert Dialog
         * @param [context] Context
         * @param [title] Int
         * @param [msg] Int
         * @param [buttonNegative] Int
         * @param [buttonPositive] Int
         * @param [buttonNeutral] Int
         * @param [clickListener] OnButtonClickListener
         * @author: Sumit Lakra
         * @date: 04/25/2019
         */
        fun ask(context: Context?, title: Int, msg: Int, buttonNegative: Int, buttonPositive: Int,
                buttonNeutral: Int, clickListener: OnButtonClickListener) {
            if (context == null)
                return
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(msg)
            if (buttonPositive > 0)
                builder.setPositiveButton(buttonPositive) { _, _ -> clickListener.onButtonClick(buttonPositive) }
            if (buttonNegative > 0)
                builder.setNegativeButton(buttonNegative) { _, _ -> clickListener.onButtonClick(buttonNegative) }
            if (buttonNeutral > 0)
                builder.setNeutralButton(buttonNeutral) { _, _ -> clickListener.onButtonClick(buttonNeutral) }
            builder.setOnCancelListener { _ -> clickListener.onButtonClick(buttonNegative) }

            builder.show()
        }

        /**
         * Function to show Alert Dialog
         * @param [context] Context
         * @param [title] Int
         * @param [msg] String
         * @param [buttonNegative] Int
         * @param [buttonPositive] Int
         * @param [buttonNeutral] Int
         * @param [clickListener] OnButtonClickListener
         * @author: Sumit Lakra
         * @date: 04/25/2019
         */
        fun ask(context: Context?, title: Int, msg: String,
                buttonNegative: Int, buttonPositive: Int, buttonNeutral: Int, clickListener: OnButtonClickListener) {
            if (context == null)
                return
            val builder = AlertDialog.Builder(context)
            builder.setTitle(title)
            builder.setMessage(msg)
            if (buttonPositive > 0)
                builder.setPositiveButton(buttonPositive) { _, _ -> clickListener.onButtonClick(buttonPositive) }
            if (buttonNegative > 0)
                builder.setNegativeButton(buttonNegative) { _, _ -> clickListener.onButtonClick(buttonNegative) }
            if (buttonNeutral > 0)
                builder.setNeutralButton(buttonNeutral) { _, _ -> clickListener.onButtonClick(buttonNeutral) }
            builder.setOnCancelListener { _ -> clickListener.onButtonClick(buttonNegative) }

            builder.show()
        }

        /**
         * Function to show Alert Dialog
         * @param [context] Context
         * @param [title] Int
         * @param [msg] Int
         * @param [buttonNegative] Int
         * @param [buttonPositive] Int
         * @param [clickListener] OnButtonClickListener
         * @author: Sumit Lakra
         * @date: 04/25/2019
         */
        fun ask(context: Context?, title: Int, msg: Int, buttonNegative: Int, buttonPositive: Int, clickListener: OnButtonClickListener) {
            ask(context, title, msg, buttonNegative, buttonPositive, 0, clickListener)
        }

        /**
         * Function to show Alert Dialog
         * @param [context] Context
         * @param [title] Int
         * @param [msg] String
         * @param [buttonNegative] Int
         * @param [buttonPositive] Int
         * @param [clickListener] OnButtonClickListener
         * @author: Sumit Lakra
         * @date: 04/25/2019
         */
        fun alert(context: Context?, title: Int, msg: Int){
            ask(context,title,msg, android.R.string.ok,0,0,object: OnButtonClickListener {
                override fun onButtonClick(id: Int) {
                    //ignore
                }
            })
        }

        /**
         * Function to show Alert Dialog with ok button
         * @param [context] Context
         * @param [title] Int
         * @param [msg] String
         * @param [buttonNegative] Int
         * @param [buttonPositive] Int
         * @param [clickListener] OnButtonClickListener
         * @author: Sumit Lakra
         * @date: 04/25/2019
         */
        fun alert(context: Context?, title: Int, msg: String){
            ask(context,title,msg, android.R.string.ok,0,0,object: OnButtonClickListener {
                override fun onButtonClick(id: Int) {
                    //ignore
                }
            })
        }

        /**
         * Function to show Alert Dialog to close app with ok button
         * @param [context] Context
         * @param [title] Int
         * @param [msg] String
         * @param [buttonNegative] Int
         * @param [buttonPositive] Int
         * @param [clickListener] OnButtonClickListener
         * @author: Sumit Lakra
         * @date: 04/25/2019
         */
        fun alertAppClose(context: Activity, title: Int, msg: String){
            ask(context,title,msg, android.R.string.ok,0,0,object: OnButtonClickListener {
                override fun onButtonClick(id: Int) {
                    context.finish()
                }
            })
        }

    }
}