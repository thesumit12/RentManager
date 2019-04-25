package com.example.components.permissions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.common.utils.Constant

/**
 * @brief: Class to check app permissions access by the user
 * @author Sumit Lakra
 * @date 04/24/19
 */
object PermissionsUtility {

    /**
     * [isPermissionGranted]
     * Function to check whether specified permission is Granted or not
     * @param [context] Context
     * @param [permission] String
     * @return Boolean
     * @author Sumit Lakra
     * @date 04/24/19
     */
    fun isPermissionGranted(context: Context, permission: String): Boolean =
        ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED

    /**
     * [isMandatoryPermissionsGranted]
     * Function to check whether all mandatory specified permission are Granted or not
     * @param [context] Context
     * @param [permissionsEnum] ArrayList<PermissionsEnum>
     * @return Boolean
     * @author Sumit Lakra
     * @date 04/24/19
     */
    fun isMandatoryPermissionsGranted(context: Context, permissionsEnum: ArrayList<String>): Boolean {
        var isGranted = true
        for (permission in permissionsEnum) {
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                isGranted = false
                break
            }
        }
        return isGranted
    }

    /**
     * [isMandatoryPermissionsDenied]
     * Function to check whether any mandatory permission is already denied
     * @param [context] Context
     * @param [permissionsEnum] ArrayList<PermissionsEnum>
     * @return Boolean
     * @author Sumit Lakra
     * @date 04/24/19
     */
    private fun isMandatoryPermissionsDenied(
        activity: AppCompatActivity,
        permissionsEnum: ArrayList<String>
    ): Boolean {
        var isDenied = false
        for (permission in permissionsEnum) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                isDenied = true
                break
            }
        }
        return isDenied
    }

    /**
     * [checkMandatoryPermissions]
     * Function to check grant status of mandatory Runtime Application Permission
     * @param [activity] AppCompatActivity
     * @param [checker] IPermissionsChecker
     * @param [permissionsEnum] ArrayList<PermissionsEnum>
     * @author Sumit Lakra
     * @date 04/24/19
     */
    fun checkMandatoryPermissions(
        activity: AppCompatActivity,
        checker: IPermissionsChecker,
        permissionsEnum: ArrayList<String>
    ) {
        when {
            isMandatoryPermissionsGranted(activity, permissionsEnum) -> checker.onPermissionGranted()
            isMandatoryPermissionsDenied(activity, permissionsEnum) ->
                openGrantPermissionsActivity(activity, permissionsEnum)
            else -> requestMandatoryPermissions(activity, permissionsEnum)
        }
    }

    /**
     * [requestMandatoryPermissions]
     * Function to launch [GrantPermissionsActivity]
     * @param [activity] AppCompatActivity
     * @param [permissionsEnum] ArrayList<PermissionsEnum>
     * @author Sumit Lakra
     * @date 04/24/19
     */
    private fun requestMandatoryPermissions(activity: AppCompatActivity, permissionsEnum: ArrayList<String>) {
        val permissionsList = arrayListOf<String>()
        for (permission in permissionsEnum) {
            permissionsList.add(permission)
        }
        val permissions = arrayOfNulls<String>(permissionsList.size)
        permissionsList.toArray(permissions)
        ActivityCompat.requestPermissions(
            activity,
            permissions,
            Constant.PERMISSIONS_REQUEST_CODE
        )
    }

    /**
     * [openGrantPermissionsActivity]
     * Function to launch [GrantPermissionsActivity]
     * @param [activity] AppCompatActivity
     * @param [permissionsEnum] ArrayList<PermissionsEnum>
     * @author vmalik
     * @date 02/11/19
     */
    fun openGrantPermissionsActivity(
        activity: AppCompatActivity,
        permissionsEnum: ArrayList<String>
    ) {
        val intent = Intent(activity, GrantPermissionsActivity::class.java)
        intent.putExtra(Constant.PERMISSIONS_REQUEST_ENUM_LIST, permissionsEnum)
        activity.startActivity(intent)
    }
}