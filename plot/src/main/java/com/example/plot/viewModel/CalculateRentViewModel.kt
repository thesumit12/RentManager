package com.example.plot.viewModel

import android.text.TextUtils
import androidx.databinding.ObservableField
import com.example.common.utils.Constant
import com.example.components.BaseViewModel
import com.example.components.common.Notif
import com.example.logging.LogHelper
import com.example.model.EventIdentifier
import com.example.plot.R
import com.google.firebase.firestore.MetadataChanges
import com.google.firebase.firestore.Source

class CalculateRentViewModel: BaseViewModel() {

    var roomNo: String = ""
    var plotType: String = ""

    val prevMainMtrReading = ObservableField<String>()
    val prevRoomMtrReading = ObservableField<String>()
    val currentMainMtrRdng = ObservableField<String>()
    val currentRoomMtrRdng = ObservableField<String>()
    val electricityBill = ObservableField<String>()
    val currentMonth = ObservableField<String>("Current Month")

    val errorMain = ObservableField<Int>()
    val errorRoom = ObservableField<Int>()

    var totalAdults = 0L
    var adults = 1

    fun getTotalAdults(online: Boolean) {
        val source = if (online) {
            Source.SERVER
        }else{
            Source.CACHE
        }
        getFireStore().collection(Constant.PLOT).document(plotType).get(source)
                .addOnSuccessListener {document->
                    if (document != null) {
                        totalAdults = document.data?.get("adults") as Long
                    }
                }.addOnFailureListener { exception->
                    LogHelper.e(exception.localizedMessage)
                }
    }

    fun calculateElectricityBill() {
        if (validateFields()) {
            if (totalAdults == 0L) {
                triggerEvent(EventIdentifier.ADULTS_ERROR)
            }else{
                val commonBill =
                        (currentMainMtrRdng.get()!!.toInt() - prevMainMtrReading.get()!!.toInt()) * 8 * adults / totalAdults
                val roomBill = (currentRoomMtrRdng.get()!!.toInt() - prevRoomMtrReading.get()!!.toInt()) * 8

                val total = commonBill + roomBill
                electricityBill.set(total.toString())
            }

        }

    }

    fun payRentClicked() {
        triggerEvent(EventIdentifier.PAY_RENT)
    }

    private fun validateFields(): Boolean {
        resetError()
        return when{
            TextUtils.isEmpty(currentMainMtrRdng.get()) ->{
                errorMain.set(R.string.empty_error)
                false
            }
            TextUtils.isEmpty(currentRoomMtrRdng.get()) -> {
                errorRoom.set(R.string.empty_error)
                false
            }
            else ->
                true
        }
    }

    private fun resetError() {
        errorRoom.set(null)
        errorMain.set(null)
    }
}