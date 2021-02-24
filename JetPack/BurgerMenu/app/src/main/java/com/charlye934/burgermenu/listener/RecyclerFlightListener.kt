package com.charlye934.burgermenu.listener

import com.charlye934.burgermenu.models.Flight

interface RecyclerFlightListener {
    fun onClick(flight: Flight, position:Int)
    fun onDelete(flight: Flight, position: Int)
}