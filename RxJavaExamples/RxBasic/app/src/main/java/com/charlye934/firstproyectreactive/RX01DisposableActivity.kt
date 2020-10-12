package com.charlye934.firstproyectreactive

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

/*
* Disposable es un desechable para desechar la subscripcion cuando un observador
* ya no quiere escuchar al observable, nos permite manejar los memory leak
* */
class RX01DisposableActivity : AppCompatActivity() {

    private lateinit var disposable:Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx01_disposable)

        val numerosObservables = Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        val numerosObserver = object : Observer<String>{
            override fun onSubscribe(d: Disposable?) {
                disposable = d!!
                Log.d(
                    "TAG1",
                    "OnSubscribe: ${Thread.currentThread().name}, Disposable: $disposable"
                )
            }

            override fun onNext(numero: String?) {
                Log.d("TAG1", "isDispose: " + disposable.isDisposed)
                Log.d("TAG1", "onNext: Numero: " + numero + " Hilo: " + Thread.currentThread().name)
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG1", "onError" + " Hilo: " + Thread.currentThread().name)
            }

            override fun onComplete() {
                Log.d("TAG1", "isDispose: " + disposable.isDisposed)
                Log.d(
                    "TAG1",
                    "onComplete: Se han emitido todos los datos" + " Hilo: " + Thread.currentThread().name
                )
            }
        }

        numerosObservables
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(numerosObserver)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("TAG1", "isDispose: " + disposable.isDisposed)
        disposable.dispose()
        Log.d("TAG1", "isDispose: " + disposable.isDisposed)
        Log.d("TAG1", "onDestroy Desechamos la subscription" + " Hilo: " + Thread.currentThread().name)
    }
}