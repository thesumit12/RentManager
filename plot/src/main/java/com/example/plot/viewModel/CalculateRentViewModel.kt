package com.example.plot.viewModel

import androidx.databinding.ObservableField
import com.example.components.BaseViewModel

class CalculateRentViewModel: BaseViewModel() {

    var roomNo: String = ""

    val prevMainMtrReading = ObservableField<String>()
    val prevRoomMtrReading = ObservableField<String>()
    val currentMainMtrRdng = ObservableField<String>()
    val currentRoomMtrRdng = ObservableField<String>()
    val electricityBill = ObservableField<String>()

    fun calculateElectricityBill() {

    }

    fun payRentClicked() {

    }
}