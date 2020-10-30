package com.charlye934.firstproyectreactive

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class RX03OperadoresActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_r_x03_operadores)

        /*OPERADORES QUE CREAN OBSERVABLES*/

        //probarJust()
        //probarJustArray()
        //probarFromArray()
        //probarRange()
        //probarRepeat()
        //probarInterval()
    }

    private fun probarBuffer(){
        Log.d("TAG1", "----------------Buffer----------------")
        val integerObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
        integerObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(3)
            .subscribe({
                for (int in it)
                    Log.d("TAG1", "Buffer item-> $it")
            }, {
                Log.d("TAG1", "onError $it")
            })

    }

    private fun probarCreateException(){
        Log.d("TAG1", "----------------Create----------------")
        Observable
            .create(ObservableOnSubscribe<Int> {
                try {
                    it.onNext(15 / 3)
                    it.onNext(3 / 0)
                } catch (e: java.lang.Exception) {
                    it.onError(e)
                }
            })
            .subscribe(
                {
                    Log.d("TAG1", "onNext $it")

                }, {
                    Log.d("TAG1", "onError: " + it.message)

                }
            )
    }

    private fun probarInterval(){
        Log.d("TAG1", "----------------Interval----------------")
        Observable
            .interval(1, TimeUnit.SECONDS)
            .take(4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG1", "onNext: $it")
            }, {
                Log.d("TAG1", "onError: $it")
            })
    }

    private fun probarCreate(){
        Log.d("TAG1", "----------------Create----------------")
        Observable
            .create(ObservableOnSubscribe<String> { emitter ->
                try {
                    Log.d("TAG1", "subscribe + hilo: " + Thread.currentThread().name)
                    emitter.onNext("A")
                    emitter.onNext("l")
                    emitter.onNext("b")
                    emitter.onNext("e")
                    emitter.onNext("r")
                    emitter.onNext("t")
                    emitter.onNext("o")
                } catch (e: Exception) {
                    emitter.onError(e)
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG1", "onNext: " + it + " hilo " + Thread.currentThread().name)

            }, {
                Log.d("TAG1", "onError: $it")
            })
    }

    private fun probarRepeat(){
        Log.d("TAG1", "----------------Repeat----------------")
        Observable
            .range(10, 3)
            .repeat(4)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG1", "Repeat->onNext: $it")
            }, {
                Log.d("TAG1", "ERROR: $it")
            })
    }

    private fun probarRange(){
        Log.d("TAG1", "----------------Range----------------")
        Observable.range(7, 17)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG1", "range->onNest: $it")

            }, {
                Log.d("TAG1", "onError: $it")
            })

    }

    private fun probarFromArray(){
        Log.d("TAG1", "----------------FromArray----------------")
        val numeros = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        Observable.fromArray(numeros)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG1", "onNext: ${it.toString()}")
            }, {
                Log.d("TAG1", "onError $it")
            }, {
                Log.d("TAG1", "onComplete")
            })
    }

    private fun probarJustArray(){
        Log.d("TAG1", "----------------JustArray----------------")
        val numeros = arrayOf("1", "2", "3", "4", "5", "6", "7", "8", "9", "10")

        Observable.just(numeros)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : Observer<Array<String>> {
                    override fun onSubscribe(d: Disposable?) {}
                    override fun onNext(t: Array<String>?) {
                        Log.d("TAG1", "JustArray->onNext " + t!!.size)
                    }

                    override fun onError(e: Throwable?) {}
                    override fun onComplete() {}

                }
            )
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
                        Log.d("TAG1", "just onError: $e")
                    }

                    override fun onComplete() {}
                }
            )
    }
}
