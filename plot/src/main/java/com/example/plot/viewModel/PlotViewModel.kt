package com.example.plot.viewModel

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.example.components.BaseViewModel
import com.google.firebase.firestore.Query
import com.example.common.utils.Constant.PLOT578
import com.example.common.utils.Constant.PLOT737
import com.example.model.EventIdentifier
import com.example.model.RoomDetails

class PlotViewModel : BaseViewModel() {

    val isDataLoaded = ObservableBoolean(false)

    val waitingDialogMessage = MutableLiveData<String>()

    fun createInitialQuery(plot: String): Query {
        return if (plot == PLOT578) {
            getFireStore().collection(PLOT578)
                    .orderBy(RoomDetails.FIELD_INDEX, Query.Direction.ASCENDING)
        }else{
            getFireStore().collection(PLOT737)
                    .orderBy(RoomDetails.FIELD_INDEX, Query.Direction.ASCENDING)

        }
    }

    fun updateView(itemCount: Int) {
        waitingDialogMessage.value = null

        when(itemCount) {
            0 -> isDataLoaded.set(false)
            else -> isDataLoaded.set(true)
        }
    }

    fun addRoomClicked() {
        triggerEvent(EventIdentifier.ADD_ROOM)
    }
}