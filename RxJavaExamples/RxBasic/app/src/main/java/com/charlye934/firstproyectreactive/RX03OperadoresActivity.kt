package com.charlye934.firstproyectreactive

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

class RX03OperadoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_r_x03_operadores)

        /*OPERADORES QUE CREAN OBSERVABLES*/

        probarJust()
    }

    private fun probarJust(){
        Log.d("TAG1", "----------------JUST----------------")
        Observable.just("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<String> {
                    override fun onSubscribe(d: Disposable?) {}
                    override fun onNext(t: String?) {
                        Log.d("TAG1", "Just->onNext $t")
                    }
                    override fun onError(e: Throwable?) {
                        Log.d("TAG1","just onError: $e")
                    }
                    override fun onComplete() {}
                }
            )
    }
}