package com.example.plot.ui

import com.google.firebase.firestore.DocumentSnapshot

interface RoomSelectedListener {

    fun onRoomClicked(room: DocumentSnapshot)
}