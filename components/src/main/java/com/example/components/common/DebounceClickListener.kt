package com.example.components.common

import android.view.View
import androidx.databinding.BindingAdapter

/**
 * This class will prevent multiple clicks being dispatched.
 * @author SUmit Lakra
 * @date 04/25/2019
 */
class DebounceClickListener(private val onClickListener: View.OnClickListener) : View.OnClickListener {
    private var lastTime: Long = 0

    override fun onClick(v: View?) {
        val current = System.currentTimeMillis()
        if (current - lastTime > DUPLICATE_CLICK_LIMIT) {
            onClickListener.onClick(v)
            lastTime = current
        }
    }

    companion object {
        const val DUPLICATE_CLICK_LIMIT = 251
        /**
         * setup safeclick listener handler
         * @author Sumit Lakra
         * @param [theze]: View, [f]: View.OnClickListener?
         * @date 04/25/19
         */
        @JvmStatic @BindingAdapter("safeClick")
        fun setOnClickListener(theze: View, f: View.OnClickListener?) {
            when (f) {
                null -> theze.setOnClickListener(null)
                else -> theze.setOnClickListener(DebounceClickListener(f))
            }
        }
    }
}
