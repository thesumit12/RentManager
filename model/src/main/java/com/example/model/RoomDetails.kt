package com.example.model

data class RoomDetails(
        var name: String = "",
        var age: String = "",
        var totalMembers: Int = 0,
        var adults: Int = 0,
        var roomRent: Int = 0,
        var joinedOn: String? = "",
        var balance: Float? = 0.0F,
        var comment: String? = "",
        var mobileNo: String = "",
        var mainMeterReading: Int = 0,
        var roomMeterReading: Int = 0,
        var index: Int = 0
        ) {

    companion object {
        const val FIELD_INDEX = "index"
    }
}