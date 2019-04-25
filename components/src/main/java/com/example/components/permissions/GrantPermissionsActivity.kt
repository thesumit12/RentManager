package com.example.components.permissions

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.common.utils.Constant.PERMISSIONS_REQUEST_ENUM_LIST
import com.example.components.R
import com.example.components.common.DebounceClickListener

/**
 * @brief: Activity to ask and grant permission in the app
 * @author Sumit Lakra
 * @date 04/25/2019
 */
class GrantPermissionsActivity : AppCompatActivity() {

    private lateinit var requestPermissionsEnum: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grant_permissions)

        @Suppress("UNCHECKED_CAST")
        requestPermissionsEnum = intent.getIntegerArrayListExtra(PERMISSIONS_REQUEST_ENUM_LIST)
                as ArrayList<String>
        setPermissionsGrantDescription(requestPermissionsEnum)

        findViewById<Button>(R.id.btn_grant).setOnClickListener(DebounceClickListener(View.OnClickListener {
            val i = Intent(
                Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
                Uri.parse("package:" + "com.example.mvvm.app"))
            i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
            startActivity(i)
        }))
    }

    override fun onResume() {
        super.onResume()

        if (PermissionsUtility.isMandatoryPermissionsGranted(this, requestPermissionsEnum)) {
            finish()
        } else {
            updatePermissionsGrantDescription()
        }
    }

    override fun onBackPressed() {
    }

    /**
     * [setPermissionsGrantDescription]
     * Function to set Permissions needed to Grant text description
     * @param [permissionsEnum] ArrayList<PermissionsEnum>
     * @author vmalik
     * @date 02/11/19
     */
    private fun setPermissionsGrantDescription(permissionsEnum: ArrayList<String>) {
        val string = StringBuilder()
        if (permissionsEnum.size > 0) {
            for (permission in permissionsEnum) {
                string.append("Please grant ${permission.replace("android.permission.","")}")
                    .append("\n\n")
            }
        }
        val grantDescription = findViewById<TextView>(R.id.grant_permission)
        grantDescription.text = string
    }

    /**
     * [updatePermissionsGrantDescription]
     * Function to update Permissions needed to Grant text description
     * @author vmalik
     * @date 02/11/19
     */
    private fun updatePermissionsGrantDescription() {
        val permissionsEnum: ArrayList<String> = ArrayList()
        permissionsEnum.addAll(requestPermissionsEnum)
        for (permission in requestPermissionsEnum) {
            if (PermissionsUtility.isPermissionGranted(this, permission)) {
                permissionsEnum.remove(permission)
            }
        }
        setPermissionsGrantDescription(permissionsEnum)
    }
}
