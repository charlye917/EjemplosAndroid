package com.charlye934.firstproyectreactive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.util.Linkify
import android.util.Log
import hu.akarnokd.rxjava2.basetypes.Perhaps.just
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class RX00IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx00_intro)

        val numerosObservable = Observable.just("1","2","3","4","5","6","7","8","9","10")

        val numerosObserver: Observer = object : io.reactivex.Observer<String>{
            override fun onSubscribe(d: Disposable?) {
                Log.d("TAG1", "ONSUBSCIRBE: $d" + "HILO: " + Thread.currentThread().name)
            }

            override fun onNext(t: String?) {
                Log.d("TAG1", "ONNEXT: $t" + "HILO: " + Thread.currentThread().name)
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG1", "ONERROR: $e" + "HILO: " + Thread.currentThread().name)
            }

            override fun onComplete() {
                Log.d("TAG1", "OnComplete" + "HILO: " + Thread.currentThread().name)
            }
        }

        numerosObservable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe(numerosObserver)

    }
}