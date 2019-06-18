package com.example.model

data class RentTransaction(
        var rentPaid: String = "",
        var totalRent: String = "",
        var balance: String = "",
        var comment: String? = "",
        var time: String = "",
        var plotType: String = ""
)