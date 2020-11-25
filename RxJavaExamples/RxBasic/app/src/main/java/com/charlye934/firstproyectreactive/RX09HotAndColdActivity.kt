package com.charlye934.firstproyectreactive

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import java.lang.Exception
import java.util.concurrent.TimeUnit

class RX09HotAndColdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx09_hot_and_cold)

        //coldObservable()
        hotObservable()
    }

    private fun hotObservable(){
        val hot = Observable.interval(500, TimeUnit.MILLISECONDS).publish()
        hot.connect()
        hot.subscribe {
            Log.d("TAG1", "Subscribe number 1: $it")
        }
        try {
            Thread.sleep(2000)
        }catch (e:Exception){}

        hot.subscribe{
            Log.d("TAG1", "Subscribe number: $it")
        }
    }

    @SuppressLint("CheckResult")
    private fun coldObservable(){
        val cold = Observable.interval(500, TimeUnit.MILLISECONDS)
        cold.subscribe {
            Log.d("TAG1", "Subscribe number 1: $it")
        }

        try {
            Thread.sleep(2000)
        }catch (e:Exception){ }

        cold.subscribe{
            Log.d("TAG1", "Subscribe number 2: $it")
        }
    }
}