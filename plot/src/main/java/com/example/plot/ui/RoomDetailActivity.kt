package com.example.plot.ui

import android.os.Bundle
import com.example.common.utils.Constant
import com.example.common.utils.ViewType
import com.example.components.BaseActivity
import com.example.components.IRouter
import com.example.model.EventIdentifier
import com.example.plot.BR
import com.example.plot.R
import com.example.plot.databinding.ActivityRoomDetailBinding
import com.example.plot.viewModel.RoomDetailViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomDetailActivity : BaseActivity<ActivityRoomDetailBinding, RoomDetailViewModel>() {

    private val roomDetailViewModel: RoomDetailViewModel by viewModel()
    private val router: IRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        roomDetailViewModel.roomNumber = intent?.extras?.getString(Constant.ROOM_NO) ?: ""
        roomDetailViewModel.plotType = intent?.extras?.getString(Constant.PLOT_TYPE) ?: ""
        setActionBar()
        subscribeObservers()

        roomDetailViewModel.getRoomDetails()
    }

    override fun getLayoutId(): Int = R.layout.activity_room_detail

    override fun getViewModel(): RoomDetailViewModel = roomDetailViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    private fun setActionBar() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = roomDetailViewModel.roomNumber
    }

    private fun subscribeObservers() {
        roomDetailViewModel.onEventReceived += {event->
            when(event.type) {
                EventIdentifier.CALCULATE_RENT -> showCalculateRentScreen()
                EventIdentifier.RENT_HISTORY -> showRentHistoryScreen()

                else -> {
                    //Do nothing
                }
            }
        }
    }

    private fun showCalculateRentScreen() {
        router.routeTo(this, ViewType.CALCULATE_RENT, Bundle().apply {
            putString(Constant.ROOM_NO, roomDetailViewModel.roomNumber)
        })
    }

    private fun showRentHistoryScreen() {

    }
}
