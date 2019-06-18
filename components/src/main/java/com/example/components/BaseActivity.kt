package com.example.components

import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.PersistableBundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.common.utils.Constant.PERMISSIONS_REQUEST_CODE
import com.example.components.common.WaitingDialogHelper
import com.example.components.permissions.IPermissionsChecker
import com.example.components.permissions.PermissionsUtility.checkMandatoryPermissions
import com.example.components.permissions.PermissionsUtility.openGrantPermissionsActivity
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

/**
 * Base activity to be extended by all activities of application
 * @author: Sumit Lakra
 * @date 04/25/19
 */
abstract class BaseActivity<T: ViewDataBinding, V : BaseViewModel>() : AppCompatActivity(), KoinComponent {
    private val router: IRouter by inject()

    private lateinit var mViewDataBinding: T
    private var mViewModel: V? = null

    private val waitingDialogHelper = WaitingDialogHelper()

    @LayoutRes
    abstract fun getLayoutId(): Int

    abstract fun getViewModel(): V

    abstract fun getBindingVariable(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    /**
     * Function to get ViewDataBinding
     * @return [T] ViewDataBinding
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    fun getViewDataBinding(): T {
        return mViewDataBinding
    }

    /**
     * Function to execute ViewDataBinding
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    /**
     * Function to show waiting dialog
     * @param [msg] String
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    fun showWaitingDialog(msg: String) {
        waitingDialogHelper.show(this, msg)
    }

    /**
     * Function to show waiting dialog
     * @param [msg] Int
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    fun showWaitingDialog(msg: Int) {
        waitingDialogHelper.show(this, msg)
    }

    /**
     * Function to hide waiting dialog
     * @author Sumit Lakra
     * @date 04/25/2019
     */
    fun hideWaitingDialog() {
        waitingDialogHelper.hideDialog()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if(item?.itemId == android.R.id.home){
            onBackPressed()
            true
        }else
            super.onOptionsItemSelected(item)
    }

    private lateinit var iPermissionsChecker: IPermissionsChecker

    /**
     * function to allow Mandatory Runtime Application Permissions
     * @param [iPermissionsChecker] IPermissionsChecker
     * @author Sumit Lakra
     * @date 04/25/19
     */
    fun allowMandatoryPermissions(
        iPermissionsChecker: IPermissionsChecker,
        permission: ArrayList<String>
    ) {
        this.iPermissionsChecker = iPermissionsChecker
        checkMandatoryPermissions(activity = this@BaseActivity, checker = iPermissionsChecker,
            permissionsEnum = permission)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (grantResults.isEmpty() || permissions.isEmpty() || requestCode != PERMISSIONS_REQUEST_CODE) {
            return
        }

        val deniedPermissions = arrayListOf<String>()
        for (i in 0 until grantResults.size) {
            addToDenialList(grantResults[i], deniedPermissions, permissions[i])
        }
        if (deniedPermissions.size > 0) {
            openGrantPermissionsActivity(activity = this@BaseActivity, permissionsEnum = deniedPermissions)
        } else {
            iPermissionsChecker.onPermissionGranted()
        }
    }

    /**
     * add denied permission to list
     * @param [grantResult] Int
     * @param [deniedPermissions] ArrayList<PermissionsEnum>
     * @param [permission] String
     * @author Sumit Lakra
     * @date 04/25/19
     */
    private fun addToDenialList(grantResult: Int, deniedPermissions: ArrayList<String>, permission: String) {
        if (grantResult == PackageManager.PERMISSION_DENIED &&
            !deniedPermissions.contains(permission)) {
            deniedPermissions.add(permission)
        }
    }

    fun isNetworkAvailable() : Boolean {
        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null && connectivityManager.activeNetworkInfo.isConnected
    }
}