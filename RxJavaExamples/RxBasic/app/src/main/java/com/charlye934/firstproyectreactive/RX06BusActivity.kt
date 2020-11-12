package com.charlye934.firstproyectreactive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_rx06_bus.*

class RX06BusActivity : AppCompatActivity() {

    private lateinit var fragment: RX06BusFragment
    private lateinit var transaction: FragmentTransaction
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx06_bus)

        fragment = RX06BusFragment()
        transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frame2, fragment)
        transaction.commit()

        compositeDisposable = CompositeDisposable()

        btRxBus.setOnClickListener {
            RX06Bus
        }
    }
}