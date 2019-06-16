package com.example.plot.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.common.utils.Constant
import com.example.components.BaseActivity
import com.example.model.EventIdentifier
import com.example.plot.BR
import com.example.plot.R
import com.example.plot.databinding.ActivityAddRoomBinding
import com.example.plot.viewModel.AddRoomViewModel
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddRoomActivity : BaseActivity<ActivityAddRoomBinding, AddRoomViewModel>() {

    private val addRoomViewModel: AddRoomViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addRoomViewModel.plotType = intent.extras?.getString(Constant.PLOT_TYPE)!!
        addRoomViewModel.isAddRoom.set(intent.extras?.getBoolean(Constant.ADD_ROOM) ?: true)
        setActionBar()
        subscribeObservers()
    }

    override fun getLayoutId(): Int = R.layout.activity_add_room

    override fun getViewModel(): AddRoomViewModel = addRoomViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    private fun setActionBar() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "AddRoom"
    }

    private fun subscribeObservers() {
        addRoomViewModel.waitingDialogMessage.observe(this, Observer {msg->
            if (msg == null) {
                hideWaitingDialog()
            }else{
                showWaitingDialog(msg)
            }
        })

        addRoomViewModel.onEventReceived += {event ->
            when(event.type) {
                EventIdentifier.ADD_ROOM_SUCCESS ->{
                    this@AddRoomActivity.finish()
                }
                EventIdentifier.ADD_ROOM_FAILURE -> {
                    toast("Failed To Add Room")
                }
                else -> {
                    //Do nothing
                }
            }

        }
    }
}
