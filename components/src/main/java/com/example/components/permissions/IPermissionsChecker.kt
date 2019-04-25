package com.example.components.permissions

/**
 * [IPermissionsChecker] Interface for callbacks for Runtime Application Permissions
 * @author Sumit Lakra
 * @date 04/24/19
 */
interface IPermissionsChecker {

    /**
     * [onPermissionGranted]
     * Function callback for Runtime Application Permission Granted status
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    fun onPermissionGranted()
}