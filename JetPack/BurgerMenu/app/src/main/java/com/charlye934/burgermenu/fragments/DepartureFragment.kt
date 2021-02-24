package com.charlye934.burgermenu.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.burgermenu.R
import com.charlye934.burgermenu.adapter.FlightAdapter
import com.charlye934.burgermenu.listener.RecyclerFlightListener
import com.charlye934.burgermenu.models.Flight
import kotlinx.android.synthetic.main.fragment_departure.*
import kotlinx.android.synthetic.main.fragment_departure.view.*

class DepartureFragment : Fragment() {

    private val list: ArrayList<Flight> by lazy { getFlights() }
    private lateinit var adapterFlight: FlightAdapter
    private val layoutManagerFlight by lazy { LinearLayoutManager(context) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.setTitle(R.string.departures_fragment_title)
        return inflater.inflate(R.layout.fragment_departure, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.setTitle(R.string.departures_fragment_title)
        setRecyclerView()
    }


    private fun setRecyclerView() {
        adapterFlight = (FlightAdapter(list, object : RecyclerFlightListener{
            override fun onClick(flight: Flight, position: Int) {
                Toast.makeText(context, "Let's go to ${flight.city}",Toast.LENGTH_SHORT).show()
            }

            override fun onDelete(flight: Flight, position: Int) {
                list.remove(flight)
                adapterFlight.notifyItemRemoved(position)
                Toast.makeText(context, "${flight.city} has been removed", Toast.LENGTH_SHORT).show()
            }

        }))

        recyclerView.apply{
            setHasFixedSize(true)
            itemAnimator = DefaultItemAnimator()
            layoutManager = layoutManagerFlight
            adapter = adapterFlight
        }
    }

    private fun getFlights(): ArrayList<Flight>{
        return object : ArrayList<Flight>() {
            init {
                add(Flight(1, "Seattle",R.drawable.seattle))
                add(Flight(2, "New York",R.drawable.new_york))
                add(Flight(3, "London",R.drawable.london))
                add(Flight(4, "Seville",R.drawable.seville))
                add(Flight(5, "Veneci",R.drawable.venice))
                add(Flight(6, "Athens",R.drawable.athens))
                add(Flight(7, "Toronto",R.drawable.toronto))
                add(Flight(8, "Dublin",R.drawable.dublin))
                add(Flight(9, "Caribe",R.drawable.caribbean_sea ))
            }
        }
    }
}