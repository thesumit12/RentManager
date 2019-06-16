package com.example.plot.ui

import android.os.Bundle
import com.example.common.utils.Constant
import com.example.components.BaseActivity
import com.example.components.IRouter
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

        setActionBar()
        subscribeObservers()
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

    }
}
