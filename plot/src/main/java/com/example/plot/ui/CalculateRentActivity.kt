package com.example.plot.ui

import android.os.Bundle
import com.example.common.utils.Constant
import com.example.common.utils.ViewType
import com.example.components.BaseActivity
import com.example.components.IRouter
import com.example.components.common.Notif
import com.example.model.EventIdentifier
import com.example.plot.BR
import com.example.plot.R
import com.example.plot.databinding.ActivityCalculateRentBinding
import com.example.plot.viewModel.CalculateRentViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CalculateRentActivity : BaseActivity<ActivityCalculateRentBinding, CalculateRentViewModel>() {

    private val calculateRentViewModel: CalculateRentViewModel by viewModel()
    private val router: IRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        calculateRentViewModel.roomNo = intent?.extras?.getString(Constant.ROOM_NO) ?: ""
        calculateRentViewModel.plotType = intent?.extras?.getString(Constant.PLOT_TYPE) ?: ""
        calculateRentViewModel.prevMainMtrReading.set(intent?.extras?.getInt(Constant.MAIN_READING).toString())
        calculateRentViewModel.prevRoomMtrReading.set(intent?.extras?.getInt(Constant.ROOM_READING).toString())
        calculateRentViewModel.adults = intent?.extras?.getInt(Constant.ADULTS) ?: 1

        setActionBar()
        subscribeObservers()
    }

    override fun onStart() {
        super.onStart()
        calculateRentViewModel.getTotalAdults(isNetworkAvailable())
    }

    override fun getLayoutId(): Int = R.layout.activity_calculate_rent

    override fun getViewModel(): CalculateRentViewModel = calculateRentViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    private fun setActionBar() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = calculateRentViewModel.roomNo
    }

    private fun subscribeObservers() {
        calculateRentViewModel.onEventReceived += {event->
            when(event.type) {
                EventIdentifier.PAY_RENT -> {
                    router.routeTo(this@CalculateRentActivity, ViewType.PAY_RENT, Bundle().apply {
                        putString(Constant.ELECTRICITY_BILL, calculateRentViewModel.electricityBill.get())
                        putString(Constant.ROOM_NO, calculateRentViewModel.roomNo)
                        putString(Constant.PLOT_TYPE, calculateRentViewModel.plotType)
                    })
                }
                EventIdentifier.ADULTS_ERROR -> Notif.alert(this@CalculateRentActivity,
                        R.string.bill_error, R.string.internet_error)
                else -> {
                    //Do Nothing
                }
            }
        }
    }
}
