package com.example.plot.viewModel

import androidx.databinding.ObservableField
import com.example.components.BaseViewModel
import com.example.logging.LogHelper
import com.example.model.EventIdentifier
import com.example.model.RoomDetails

class RoomDetailViewModel : BaseViewModel() {

    val name = ObservableField<String>()
    val mobileNumber = ObservableField<String>()
    val totalMembers = ObservableField<String>()
    val rent = ObservableField<String>()
    val balance = ObservableField<String>()
    val comment = ObservableField<String>()

    var roomNumber: String = ""
    var plotType: String = ""


    fun getRoomDetails() {
        getFireStore().collection(plotType).document(roomNumber).get()
                .addOnSuccessListener {document->
                    val roomDetails = document.toObject(RoomDetails::class.java)
                    name.set(roomDetails?.name)
                    mobileNumber.set(roomDetails?.mobileNo)
                    totalMembers.set(roomDetails?.totalMembers.toString())
                    rent.set(roomDetails?.roomRent.toString())
                    balance.set(roomDetails?.balance.toString())
                    comment.set(roomDetails?.comment)
                    }
                .addOnFailureListener {exception ->  
                    LogHelper.e(exception.localizedMessage)
                }
    }

    fun calculateRentClicked() {
        triggerEvent(EventIdentifier.CALCULATE_RENT)
    }

    fun rentHistoryClicked() {
        triggerEvent(EventIdentifier.RENT_HISTORY)
    }
}