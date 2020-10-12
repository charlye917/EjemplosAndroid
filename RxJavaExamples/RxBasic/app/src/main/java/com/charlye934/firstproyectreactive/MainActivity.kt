package com.charlye934.firstproyectreactive

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupView()
    }

    private fun setupView(){
        btRX00Introduccion.setOnClickListener {
            startActivity(Intent(this,RX00IntroActivity::class.java))
        }
        btRX01Disposable.setOnClickListener {
            startActivity(Intent(this, RX01DisposableActivity::class.java))
        }
    }
}