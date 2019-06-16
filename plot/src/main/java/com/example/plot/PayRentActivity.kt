package com.example.plot

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.components.IRouter
import com.example.plot.viewModel.PayRentViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class PayRentActivity : AppCompatActivity() {

    private val payRentViewModel: PayRentViewModel by viewModel()
    private val router: IRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_rent)
    }
}
