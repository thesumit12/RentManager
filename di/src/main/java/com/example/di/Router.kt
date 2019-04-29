package com.example.di

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.common.utils.ViewType
import com.example.components.BaseActivity
import com.example.components.BaseFragment
import com.example.components.IRouter

/**
@file_name: Router.kt
@author: Sumit Lakra
@brief: Router class to manage app navigation.
@date: 04/29/2019
 */
class Router : IRouter {

    /**
     * Navigate to particular activity with startforresult
     * @param caller BaseActivity
     * @param destinationType Int
     * @param bundle Bundle
     * @param requestCode Int
     * @author Sumit Lakra
     * @date 04/29/19
     */
    override fun routeForResult(caller: BaseActivity<*, *>, destinationType: Int, bundle: Bundle, requestCode: Int) {
        /*when(destinationType) {
            //ViewType.TEST_KEY -> caller.startActivityForResult(getIntent(caller, Activity, bundle), requestCode)
        }*/
    }

    /**
     * Navigate to particular activity with startforresult
     * @param caller BaseFragment
     * @param destinationType Int
     * @param bundle Bundle
     * @param requestCode Int
     * @author Sumit Lakra
     * @date 04/29/19
     */
    override fun routeForResult(caller: BaseFragment<*, *>, destinationType: Int, bundle: Bundle, requestCode: Int) {

    }

    /**
     * Navigate to particular activity
     * @param caller BaseActivity
     * @param destinationType Int
     * @param bundle Bundle
     * @param requestCode Int
     * @author Sumit Lakra
     * @date 04/29/19
     */
    override fun routeTo(caller: BaseActivity<*, *>, destinationType: Int, bundle: Bundle) {

    }

    /**
     * Navigate to particular activity
     * @param caller Context
     * @param destinationType Int
     * @param bundle Bundle
     * @param requestCode Int
     * @author Sumit Lakra
     * @date 04/29/19
     */
    override fun routeTo(caller: Context, destinationType: Int, bundle: Bundle) {

    }

    /**
     * called when navigation is needed from one to another activity with flag for intent
     * @param [caller] BaseActivity
     * @param [destinationType] Int
     * @param [bundle] Bundle
     * @param [flag] Int
     * @author Sumit Lakra
     * @date 04/29/19
     */
    override fun routeToWithFlag(caller: BaseActivity<*, *>, destinationType: Int, bundle: Bundle, flag: Int) {

    }

    /**
     * generate intent based on given inputs.
     * @param caller BaseActivity
     * @param destination Class<T>
     * @param bundle Bundle
     * @return Intent
     * @author pkumar
     * @date 01/23/19
     */
    private fun <T> getIntent(caller: BaseFragment<*,*>, destination: Class<T>, bundle: Bundle): Intent {
        val intent = Intent(caller.context, destination)
        intent.putExtras(bundle)
        return intent
    }

    /**
     * generate intent based on given inputs.
     * @param caller BaseActivity
     * @param destination Class<T>
     * @param bundle Bundle
     * @return Intent
     * @author sbhushan
     * @date 01/11/19
     */
    private fun <T> getIntent(caller: Context, destination: Class<T>, bundle: Bundle): Intent {
        val intent = Intent(caller, destination)
        intent.putExtras(bundle)
        return intent
    }

    /**
     * generate intent based on given inputs.
     * @param caller BaseActivity
     * @param destination Class<T>
     * @param bundle Bundle
     * @return Intent
     * @author sbhushan
     * @date 01/11/19
     */
    private fun <T> getIntent(caller: Context, destination: Class<T>, bundle: Bundle, flag: Int): Intent {
        val intent = Intent(caller, destination)
        intent.putExtras(bundle)
        intent.flags = flag
        return intent
    }
}