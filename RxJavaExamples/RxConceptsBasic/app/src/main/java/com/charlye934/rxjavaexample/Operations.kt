package com.charlye934.rxjavaexample

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.*

class Operations {

    val mList = mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12)
    val arrayNum = arrayOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12)
    val arrayNum2 = arrayOf<Int>(10,20,30,40,50,6,7,8,9,10,11,12)


    /*
    * JUST OPERATOR
    * create an Observable that emits a particular item
    * http://reactivex.io/documentation/operators/just.html
    * */
    fun justOperator(){
        val observable = Observable.just(mList)
        val observer = object : Observer<List<Int>> {
            override fun onComplete() {
                Log.d(MainActivity.TAG,"onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(MainActivity.TAG,"onSubscribe : $d")
            }

            override fun onError(e: Throwable) {
                Log.d(MainActivity.TAG,"onError: $e")
            }

            override fun onNext(t: List<Int>) {
                Log.d(MainActivity.TAG,"onNext: $t")
            }
        }

        observable.subscribe(observer)
    }

    /*
    * FROM OPERATOR
    * convert various other objects and data types into Observables
    * http://reactivex.io/documentation/operators/from.html
    * */
    fun fromOperator(){
        val observable = Observable.fromArray(arrayNum, arrayNum2)
        val observer = object : Observer<Array<Int>>{
            override fun onComplete() {
                Log.d(MainActivity.TAG,"onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(MainActivity.TAG,"onSubscribe : $d")
            }

            override fun onError(e: Throwable) {
                Log.d(MainActivity.TAG,"onError: $e")
            }

            override fun onNext(t: Array<Int>) {
                Log.d(MainActivity.TAG,"onNext: ${t.contentToString()}")
            }
        }

        observable.subscribe(observer)
    }

    fun fromIterableOperator(){
        val observable = Observable.fromIterable(mList)
        val observer = object : Observer<Int>{
            override fun onComplete() {
                Log.d(MainActivity.TAG,"onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.d(MainActivity.TAG,"onSubscribe : $d")
            }

            override fun onError(e: Throwable) {
                Log.d(MainActivity.TAG,"onError: $e")
            }

            override fun onNext(t: Int) {
                Log.d(MainActivity.TAG,"onNext: ${t}")
            }
        }

        observable.subscribe(observer)
    }

    @SuppressLint("CheckResult")
    fun rangeOperator(){
        Observable.range(1,16)
            ?.subscribe(
                {
                    Log.d(MainActivity.TAG, "onNext: $it")
                },
                {
                    Log.d(MainActivity.TAG, "onError: $it")
                },{
                    Log.d(MainActivity.TAG, "onComplete")
                }
            )
    }

}