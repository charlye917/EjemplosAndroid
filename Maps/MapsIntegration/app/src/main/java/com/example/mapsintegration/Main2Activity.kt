package com.example.mapsintegration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val fManager = supportFragmentManager

        val transaction = fManager.beginTransaction()
        transaction.add(R.id.fragmentContainer, MapFragment())
        transaction.commit()
    }
}
