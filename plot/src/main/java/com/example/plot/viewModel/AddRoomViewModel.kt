package com.example.plot.viewModel

import android.text.TextUtils
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.common.utils.Constant
import com.example.components.BaseViewModel
import com.example.logging.LogHelper
import com.example.model.EventIdentifier
import com.example.model.RoomDetails
import com.example.plot.R
import com.google.firebase.firestore.SetOptions

class AddRoomViewModel : BaseViewModel() {
    val roomNo = ObservableField<String>()
    val name = ObservableField<String>()
    val age = ObservableField<String>()
    val totalMembers = ObservableField<String>()
    val adults = ObservableField<String>()
    val rent = ObservableField<String>()
    val joinedOn = ObservableField<String>()
    val balance = ObservableField<String>()
    val comment = ObservableField<String>()
    val mobileNo = ObservableField<String>()
    val mainMtrRdng = ObservableField<String>()
    val roomMtrRdng = ObservableField<String>()

    val errorRoomNo = ObservableField<Int>()
    val errorName = ObservableField<Int>()
    val errorAge = ObservableField<Int>()
    val errorTotalMembers = ObservableField<Int>()
    val errorAdults = ObservableField<Int>()
    val errorRent = ObservableField<Int>()
    val errorMobileNo = ObservableField<Int>()
    val errorMainMtrRdng = ObservableField<Int>()
    val errorRoomMtrRdng = ObservableField<Int>()

    val waitingDialogMessage = MutableLiveData<String>()
    lateinit var plotType: String
    var isAddRoom = ObservableBoolean(true)

    fun addRoom() {
        if (validateFields()) {
            waitingDialogMessage.value = "Adding Room.."
            val roomDetails = RoomDetails()
            roomDetails.index = roomNo.get()!!.toInt()
            roomDetails.name = name.get()!!
            roomDetails.age = age.get()!!
            roomDetails.mobileNo = mobileNo.get()!!
            roomDetails.totalMembers = totalMembers.get()!!.toInt()
            roomDetails.adults = adults.get()!!.toInt()
            roomDetails.joinedOn = joinedOn.get()
            roomDetails.balance = balance.get()!!.toFloat()
            roomDetails.mainMeterReading = mainMtrRdng.get()!!.toInt()
            roomDetails.roomMeterReading = roomMtrRdng.get()!!.toInt()
            roomDetails.comment = comment.get()

            if (isAddRoom.get()) {
                getFireStore().collection(plotType).document("Room${roomDetails.index}")
                        .set(roomDetails, SetOptions.merge())
                        .addOnSuccessListener {
                            waitingDialogMessage.value = null
                            triggerEvent(EventIdentifier.ADD_ROOM_SUCCESS)
                        }.addOnFailureListener {exception->
                            waitingDialogMessage.value = null
                            LogHelper.e(exception.localizedMessage)
                            triggerEvent(EventIdentifier.ADD_ROOM_FAILURE)
                        }
            }else{
                getFireStore().collection(plotType).document("Room${roomDetails.index}")
                        .update(mapOf(
                                "name" to roomDetails.name,
                                "mobileNo" to roomDetails.mobileNo,
                                "totalMembers" to roomDetails.totalMembers,
                                "age" to roomDetails.age,
                                "roomRent" to roomDetails.roomRent,
                                "adults" to roomDetails.adults,
                                "mainMeterReading" to roomDetails.mainMeterReading,
                                "roomMeterReading" to roomDetails.roomMeterReading
                        ))
            }

        }
    }

    private fun validateFields() : Boolean {
        resetError()
        return when {
            TextUtils.isEmpty(roomNo.get()) -> {
                errorRoomNo.set(R.string.empty_error)
                false
            }
            TextUtils.isEmpty(name.get()) -> {
                errorName.set(R.string.empty_error)
                false
            }
            TextUtils.isEmpty(age.get()) -> {
                errorAge.set(R.string.empty_error)
                false
            }
            TextUtils.isEmpty(mobileNo.get()) -> {
                errorMobileNo.set(R.string.empty_error)
                return false
            }
            TextUtils.isEmpty(totalMembers.get()) -> {
                errorTotalMembers.set(R.string.empty_error)
                return false
            }
            TextUtils.isEmpty(adults.get()) -> {
                errorAdults.set(R.string.empty_error)
                return false
            }
            TextUtils.isEmpty(rent.get()) -> {
                errorRent.set(R.string.empty_error)
                return false
            }
            TextUtils.isEmpty(mainMtrRdng.get()) -> {
                errorMainMtrRdng.set(R.string.empty_error)
                return false
            }
            TextUtils.isEmpty(roomMtrRdng.get()) -> {
                errorRoomMtrRdng.set(R.string.empty_error)
                return false
            }
            else -> true
        }
    }

    private fun resetError() {
        errorRoomNo.set(null)
        errorName.set(null)
        errorAge.set(null)
        errorTotalMembers.set(null)
        errorAdults.set(null)
        errorRent.set(null)
        errorMobileNo.set(null)
        errorMainMtrRdng.set(null)
        errorRoomMtrRdng.set(null)
    }
}
