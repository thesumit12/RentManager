package com.example.components

import android.content.Context
import android.os.Bundle

/**
 * @brief: interface containg listeners for router functions
 * @author: Sumit Lakra
 * @date: 04/25/19
 */
interface IRouter {

    /**
     * called when navigation is needed from one to another activity
     * @param [caller] Context
     * @param [destinationType] Int
     * @param [bundle] Bundle
     * @author Sumit Lakra
     * @date 04/25/19
     */
    fun routeTo(
        caller: Context,
        destinationType: Int,
        bundle: Bundle
    )

    /**
     * called when navigation is needed from one to another activity
     * @param [caller] BaseActivity
     * @param [destinationType] Int
     * @param [bundle] Bundle
     * @author Sumit Lakra
     * @date 04/25/19
     */
    fun routeTo(
        caller: BaseActivity<*,*>,
        destinationType: Int,
        bundle: Bundle
    )

    /**
     * called when navigation is needed from one to another activity with result
     * @param [caller] BaseActivity
     * @param [destinationType] Int
     * @param [bundle] Bundle
     * @param [requestCode] Int
     * @author Sumit Lakra
     * @date 04/25/19
     */
    fun routeForResult(
        caller: BaseActivity<*,*>,
        destinationType: Int,
        bundle: Bundle,
        requestCode: Int
    )

    /**
     * called when navigation is needed from one to another activity with result
     * @param [caller] BaseFragment
     * @param [destinationType] Int
     * @param [bundle] Bundle
     * @param [requestCode] Int
     * @author Sumit Lakra
     * @date 04/25/19
     */
    fun routeForResult(
        caller: BaseFragment<*, *>,
        destinationType: Int,
        bundle: Bundle,
        requestCode: Int
    )

    /**
     * called when navigation is needed from one to another activity with flag for intent
     * @param [caller] BaseActivity
     * @param [destinationType] Int
     * @param [bundle] Bundle
     * @param [flag] Int
     * @author Sumit Lakra
     * @date 04/25/19
     */
    fun routeToWithFlag(
        caller: BaseActivity<*, *>,
        destinationType: Int,
        bundle: Bundle,
        flag: Int
    )
}
