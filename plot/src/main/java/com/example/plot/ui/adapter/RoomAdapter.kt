package com.example.plot.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.model.RoomDetails
import com.example.plot.R
import com.example.plot.ui.RoomSelectedListener
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import kotlinx.android.synthetic.main.room_item.view.*

open class RoomAdapter(query: Query, private val listener: RoomSelectedListener)
    : FireStoreAdapter<RoomAdapter.ViewHolder>(query){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.room_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getSnapshot(position), listener)
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(snapshot: DocumentSnapshot, listener: RoomSelectedListener?) {
            val room = snapshot.toObject(RoomDetails::class.java) ?: return

            itemView.tv_roomNumber.text = "Room ${room.index}"
            itemView.setOnClickListener { listener?.onRoomClicked(snapshot) }
        }
    }
}