package com.example.plot.viewModel

import androidx.databinding.ObservableField
import com.example.components.BaseViewModel
import com.example.logging.LogHelper
import com.example.model.EventIdentifier
import com.example.model.RoomDetails
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.MetadataChanges

class RoomDetailViewModel : BaseViewModel() {

    val name = ObservableField<String>()
    val mobileNumber = ObservableField<String>()
    val totalMembers = ObservableField<String>()
    val rent = ObservableField<String>()
    val balance = ObservableField<String>()
    val comment = ObservableField<String>()

    var roomNumber: String = ""
    var plotType: String = ""

    var mainMeterReading = 0
    var roomMeterReading = 0
    var adults = 1


    fun getRoomDetails(): ListenerRegistration {

        return getFireStore().collection(plotType).document(roomNumber)
                .addSnapshotListener(MetadataChanges.INCLUDE){ snapshot, exception ->
            if (exception != null){
                LogHelper.w("Listen failed ${exception.localizedMessage}")
                return@addSnapshotListener
            }
            if (snapshot != null && snapshot.exists()) {
                val roomDetails = snapshot.toObject(RoomDetails::class.java)
                name.set(roomDetails?.name)
                mobileNumber.set(roomDetails?.mobileNo)
                totalMembers.set(roomDetails?.totalMembers.toString())
                rent.set(roomDetails?.roomRent.toString())
                balance.set(roomDetails?.balance.toString())
                comment.set(roomDetails?.comment)

                mainMeterReading = roomDetails?.mainMeterReading!!
                roomMeterReading = roomDetails.roomMeterReading
                adults = roomDetails.adults
            }
        }
    }

    fun calculateRentClicked() {
        triggerEvent(EventIdentifier.CALCULATE_RENT)
    }

    fun rentHistoryClicked() {
        triggerEvent(EventIdentifier.RENT_HISTORY)
    }
}