package com.example.plot.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.common.utils.Constant
import com.example.common.utils.ViewType
import com.example.components.BaseActivity
import com.example.components.IRouter
import com.example.logging.LogHelper
import com.example.model.EventIdentifier
import com.example.plot.BR
import com.example.plot.R
import com.example.plot.databinding.ActivityPlotBinding
import com.example.plot.ui.adapter.RoomAdapter
import com.example.plot.viewModel.PlotViewModel
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.activity_plot.*
import org.jetbrains.anko.toast
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.android.ext.android.inject

class PlotActivity : BaseActivity<ActivityPlotBinding, PlotViewModel>(), RoomSelectedListener {

    val plotViewModel: PlotViewModel by viewModel()
    private lateinit var mBinding: ActivityPlotBinding
    private lateinit var adapter: RoomAdapter
    private lateinit var query: Query
    private lateinit var plotType: String

    private val router: IRouter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = getViewDataBinding()
        plotType = intent.extras?.getString(Constant.PLOT_TYPE)!!

        setActionBar()
        subscribeObservers()
        initializeRecyclerView()
    }

    override fun getLayoutId(): Int = R.layout.activity_plot

    override fun getViewModel(): PlotViewModel = plotViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    private fun setActionBar() {
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = plotType
    }

    private fun subscribeObservers() {
        plotViewModel.waitingDialogMessage.observe(this, Observer {msg ->
            if (msg == null) {
                hideWaitingDialog()
            }else{
                showWaitingDialog(msg)
            }
        })

        plotViewModel.onEventReceived += { event ->
            when(event.type) {
                EventIdentifier.ADD_ROOM -> openAddRoomPage()
                else -> {
                    //Do nothing
                }
            }
        }
    }

    private fun openAddRoomPage() {
        router.routeTo(this, ViewType.ADD_ROOM, Bundle().apply {
            putString(Constant.PLOT_TYPE, plotType)
            putBoolean(Constant.ADD_ROOM, true)
        })
    }

    private fun initializeRecyclerView() {
        query = plotViewModel.createInitialQuery(plotType)

        adapter = object : RoomAdapter(query, this@PlotActivity){
            override fun onDataChanged() {
                plotViewModel.updateView(itemCount)
            }

            override fun onError(e: FirebaseFirestoreException) {
                LogHelper.e(e.toString())
            }
        }

        rv_room.layoutManager = GridLayoutManager(this, getNoOfColumns())
        rv_room.adapter = adapter
    }

    override fun onRoomClicked(room: DocumentSnapshot) {
        router.routeTo(this, ViewType.ROOM_DETAILS, Bundle().apply {
            putString(Constant.PLOT_TYPE, plotType)
            putString(Constant.ROOM_NO, room.id)
        })
    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    private fun getNoOfColumns(): Int {
        val displayMetrics = resources.displayMetrics
        val screenWidthDp = displayMetrics.widthPixels / displayMetrics.density
        return (screenWidthDp / 120 + 0.5).toInt()
    }
}
