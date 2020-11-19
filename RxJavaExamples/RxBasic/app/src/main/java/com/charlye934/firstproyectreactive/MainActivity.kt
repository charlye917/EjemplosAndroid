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
        btRX02CompositeDisposable.setOnClickListener {
            startActivity(Intent(this, RX02CompositeDisposableActivity::class.java))
        }
        btRX03Operadores.setOnClickListener {
            startActivity(Intent(this, RX03OperadoresActivity::class.java))
        }
        btRX04TipoObservables.setOnClickListener {
            startActivity(Intent(this, RX04TiposObservablesActivity::class.java))
        }
        btRX05Subject.setOnClickListener {
            startActivity(Intent(this, RX05SubjectActivity::class.java))
        }
        btRX06Bus.setOnClickListener {
            startActivity(Intent(this, RX06BusActivity::class.java))
        }
        btRX07Binding.setOnClickListener {
            startActivity(Intent(this, RX07BindingActivity::class.java))
        }
        btRX08BackPressure.setOnClickListener {
            startActivity(Intent(this, RX08BackPressureActivity::class.java))
        }
    }
}