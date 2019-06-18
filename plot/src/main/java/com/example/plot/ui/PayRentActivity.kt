package com.example.plot.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.common.utils.Constant
import com.example.common.utils.ViewType
import com.example.components.BaseActivity
import com.example.components.IRouter
import com.example.components.common.Notif
import com.example.model.EventIdentifier
import com.example.model.EventType
import com.example.plot.BR
import com.example.plot.R
import com.example.plot.databinding.ActivityPayRentBinding
import com.example.plot.viewModel.PayRentViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PayRentActivity : BaseActivity<ActivityPayRentBinding, PayRentViewModel>() {

    private val payRentViewModel: PayRentViewModel by viewModel()
    private val router: IRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        payRentViewModel.roomNo = intent?.extras?.getString(Constant.ROOM_NO) ?: "-"
        payRentViewModel.plotType = intent?.extras?.getString(Constant.PLOT_TYPE) ?: "-"
        payRentViewModel.electricityBill = intent?.extras?.getString(Constant.ELECTRICITY_BILL) ?: "-"

        setActionBar()
        subscribeObservers()
    }

    override fun onStart() {
        super.onStart()
        payRentViewModel.getRentDetails()
    }

    override fun getLayoutId(): Int = R.layout.activity_pay_rent

    override fun getViewModel(): PayRentViewModel = payRentViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    private fun setActionBar() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = payRentViewModel.roomNo
    }

    private fun subscribeObservers() {
        payRentViewModel.waitingDialogMsg.observe(this, Observer {msg ->
            if (msg == null)
                hideWaitingDialog()
            else
                showWaitingDialog(msg)
        })

        payRentViewModel.errorMsg.observe(this, Observer {error->
            if (error != null)
                Notif.alert(this, R.string.error_title, error)
        })

        payRentViewModel.onEventReceived += {event ->
            when(event.type) {
                EventIdentifier.PAY_RENT_SUCCESS -> navigateToDetailPage()
                else -> {
                    //Do Nothing
                }
            }
        }
    }

    private fun navigateToDetailPage() {
        router.routeTo(this, ViewType.ROOM_DETAILS, Bundle().apply {
            putString(Constant.PLOT_TYPE, payRentViewModel.plotType)
            putString(Constant.ROOM_NO, payRentViewModel.roomNo)
        })
    }
}
