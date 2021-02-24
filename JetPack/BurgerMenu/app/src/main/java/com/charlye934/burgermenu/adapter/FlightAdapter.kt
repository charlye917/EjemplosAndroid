package com.charlye934.burgermenu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.burgermenu.R
import com.charlye934.burgermenu.listener.RecyclerFlightListener
import com.charlye934.burgermenu.models.Flight
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recycler_flight.view.*

class FlightAdapter(private val flights: List<Flight>, private val listener: RecyclerFlightListener): RecyclerView.Adapter<FlightAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_flight, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(flights[position], listener)
    }

    override fun getItemCount(): Int = flights.size

    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(flight: Flight, listener: RecyclerFlightListener) = with(itemView){
            textViewCityName.text = flight.city
            Picasso.get()
                    .load(flight.imgResources)
                    .into(imageViewBg)
            //clicks events
            setOnClickListener { listener.onClick(flight, adapterPosition) }
            buttonDelete.setOnClickListener { listener.onDelete(flight, adapterPosition) }
        }
    }
}