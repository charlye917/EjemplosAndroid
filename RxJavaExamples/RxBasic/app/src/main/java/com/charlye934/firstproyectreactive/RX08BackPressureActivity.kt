package com.charlye934.firstproyectreactive

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Flowable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class RX08BackPressureActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx08_back_pressure)
    }

    @SuppressLint("CheckResult")
    private fun backPressureDrop(){
        val source = Flowable.interval(1, TimeUnit.MILLISECONDS)
        source
            .onBackpressureDrop()
            .observeOn(Schedulers.computation())
            .subscribe({
                try {
                    Log.d("TAG1", "Consumiendo Observables: $it")
                    Thread.sleep(100)
                } catch (ex: Exception) {
                }
            }, {
                Log.d("TAG1", "onError: $it")
            })
    }

    @SuppressLint("CheckResult")
    private fun backPressureBuffer(){
        val source = Flowable.interval(1, TimeUnit.MILLISECONDS)
        source
            .onBackpressureBuffer(1000)
            .observeOn(Schedulers.computation())
            .subscribe({
                try {
                    Log.d("TAG1", "Consumiendo Observables: $it")
                    Thread.sleep(100)
                } catch (e: Exception) {

                }
            }, {
                Consumer { error: Throwable ->
                    Log.d(
                        "TAG1",
                        "onError: $error"
                    )
                }

            })
    }

    @SuppressLint("CheckResult")
    private fun generarBackPressure(){
        val source = PublishSubject.create<Int>()
        source
            .observeOn(Schedulers.io())
            .subscribe({
                Log.d("TAG1", "onNext: $it")
            }, {
                Log.d("TAG1", "onError: $it")
            }, {
                Log.d("TAG1", "onComplete")
            }, {
                Log.d("TAG1", "onSubscribe: $it")
            })

        for(i in 0 until 10){
            source.onNext(i)
            Log.d("TAG1", "creando item observable: " + 1)
        }

        source.onComplete()
    }

    private fun operacionLargaDuracion(entero: Int){
        try {
            Thread.sleep(1000)
        }catch (e: InterruptedException){
            e.printStackTrace()
        }
        Log.d("TAG1", "Consumiendo Observable: $entero")
    }
}