package com.example.plot.viewModel

import android.text.TextUtils
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.common.utils.Constant
import com.example.components.BaseViewModel
import com.example.components.common.DateUtil
import com.example.logging.LogHelper
import com.example.model.EventIdentifier
import com.example.model.RentTransaction
import com.example.model.RoomDetails
import com.example.plot.R

class PayRentViewModel: BaseViewModel() {

    var roomNo = ""
    var plotType = ""

    var electricityBill = ""
    val currentMonth = DateUtil.getCurrentMonth()
    var roomRent = ObservableField<String>("-")
    var balance = ObservableField<String>("0")
    var total = ObservableField<String>("-")

    val paidAmount = ObservableField<String>()
    val comment = ObservableField<String>()

    val errorRent = ObservableField<Int>()

    val waitingDialogMsg = MutableLiveData<String>()
    val errorMsg = MutableLiveData<String>()

    var isOnline: Boolean = false

    fun getRentDetails() {
        waitingDialogMsg.value = "Fetching Rent Details"
        getFireStore().collection(plotType).document(roomNo).get()
                .addOnSuccessListener {document ->
                    waitingDialogMsg.value = null
                    errorMsg.value = null
                    if (document != null) {
                        val roomDetails = document.toObject(RoomDetails::class.java)
                        roomRent.set(roomDetails?.roomRent!!.toString())
                        balance.set(roomDetails.balance!!.toString())
                        comment.set(roomDetails.comment)
                    }
                }.addOnFailureListener {exception ->
                    waitingDialogMsg.value = null
                    errorMsg.value = exception.localizedMessage
                    LogHelper.e(exception.localizedMessage)
                }
    }

    fun payRent() {
        resetError()
        if (validateFields()) {
            val rentTransaction = RentTransaction()
            val bal = (total.get()!!.toInt() - paidAmount.get()!!.toInt())
            rentTransaction.rentPaid = paidAmount.get()!!
            rentTransaction.totalRent = total.get()!!
            rentTransaction.balance = bal.toString()
            rentTransaction.comment = comment.get()
            rentTransaction.time = DateUtil.getCurrentTimeStamp()
            rentTransaction.plotType = plotType

            if (isOnline) {
                waitingDialogMsg.value = "Paying Rent.."
                getFireStore().collection(Constant.RENT_TRANSACTION).document(roomNo)
                        .collection(Constant.MONTH).document(DateUtil.getCurrentMonth())
                        .set(rentTransaction)
                        .addOnSuccessListener {
                            waitingDialogMsg.value = null
                            triggerEvent(EventIdentifier.PAY_RENT_SUCCESS)
                        }.addOnFailureListener {exception->
                            triggerEvent(EventIdentifier.PAY_RENT_FAILURE)
                            errorMsg.value = exception.localizedMessage
                            LogHelper.e(exception.localizedMessage)
                        }
            }

        }
    }

    private fun validateFields(): Boolean {
        return when{
            TextUtils.isEmpty(paidAmount.get()) -> {
                errorRent.set(R.string.empty_error)
                false
            }
            else -> return true
        }
    }

    private fun resetError() {
        errorRent.set(null)
    }
}