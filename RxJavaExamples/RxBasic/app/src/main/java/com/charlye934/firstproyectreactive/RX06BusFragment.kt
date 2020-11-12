package com.charlye934.firstproyectreactive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_r_x06_bus.*

class RX06BusFragment : Fragment() {

    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_r_x06_bus, container, false)
        configFragment(view)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun configFragment(view: View){
        compositeDisposable = CompositeDisposable()
        val observable = RX06Bus.instance!!.events
        compositeDisposable.add(
            observable.subscribe{
                tvFragment.text = it.toString()
            }
        )

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}