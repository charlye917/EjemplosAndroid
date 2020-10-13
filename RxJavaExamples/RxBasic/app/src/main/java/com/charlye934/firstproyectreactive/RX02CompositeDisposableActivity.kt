package com.charlye934.firstproyectreactive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.observers.DisposableObserver
import io.reactivex.rxjava3.schedulers.Schedulers

class RX02CompositeDisposableActivity : AppCompatActivity() {

    private lateinit var numerosObserver: DisposableObserver<String>
    private lateinit var numeroLetraObserver:DisposableObserver<String>
    private lateinit var numeroObservable: Observable<String>
    private lateinit var numeroLetraObservable:Observable<String>
    private lateinit var compositeDisposable: CompositeDisposable


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx02_composite_disposable)

        compositeDisposable = CompositeDisposable()
        numeroObservable = Observable.just("1","2","3","4","5","6","7","8","9","10")
        numeroLetraObservable = Observable.just("uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve","diez")

        numeroLetraObserver = object: DisposableObserver<String>(){
            override fun onNext(t: String?) {
                Log.d("TAG1", "onNext: $t")
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG1", "onError: $e")
            }

            override fun onComplete() {
                Log.d("TAG1", "onComplete")
            }
        }

        numeroLetraObserver = object : DisposableObserver<String>(){
            override fun onNext(t: String?) {
                Log.d("TAG1", "onNextLetra: $t")
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG1", "onErrorLetra: $e")
            }

            override fun onComplete() {
                Log.d("TAG1", "onCompleteLetra")
            }
        }

        compositeDisposable.add(
            numeroObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(numerosObserver)
        )

        compositeDisposable.add(
            numeroLetraObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(numeroLetraObserver)
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()

    }
}