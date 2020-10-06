package com.charlye934.rxjavaexample

import android.annotation.SuppressLint
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.Exception
import java.util.*
import java.util.concurrent.TimeUnit

class Operations {

    val mList = mutableListOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12)
    val arrayNum = arrayOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12)
    val arrayNum2 = arrayOf<Int>(10,20,30,40,50,6,7,8,9,10,11,12)
    val mUser = mutableListOf<User>(
        User(1,"demo1",15),
        User(2,"demo2",16),
        User(3,"demo3",17),
        User(4,"demo4",18),
        User(5,"demo5",19),
        User(6,"demo6",20)
    )

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
    

    @SuppressLint("CheckResult")
    fun repeatOperator(){
        Observable.range(1, 10).repeat(2)
        .subscribe(
            {
                Log.d(MainActivity.TAG, "Oncomplete: $it")
            },
            {
                Log.d(MainActivity.TAG, "onError: $it")
            },{
                Log.d(MainActivity.TAG, "onComplete")
            })
    }

    /*
    * Interval create an Observable that emits a sequence of integers spaced by a given
    * time interval
    * http://reactivex.io/documentation/operators/interval.html
    * */
    fun intervalOperator(): Observable<Long>{
        return Observable.interval(2, TimeUnit.SECONDS)
            .takeWhile { value ->
                value <= 10
            }
    }

    /*
    * Create an observable that emits a particular item after a given delay
    * http://reactivex.io/documentation/operators/timer.html
    * */
    fun timerOperator(): Observable<Long>? {
        return Observable.timer(5, TimeUnit.SECONDS)
    }

    /*
    * Crear un observable desde cero mediante una funcion
    * http://reactivex.io/documentation/operators/create.html
    * */
    fun createOperator(): Observable<Int>{
        return  Observable.create {
            try {
                for(i in mList){
                    it.onNext(i * 5)
                }

                it.onComplete()
            }catch (e: Exception){
                it.onError(e)
            }
        }
    }

    /*
    * emit only those items from an Observable that pass a predicate test
    * http://reactivex.io/documentation/operators/filter.html
    * */
    fun filterOperator(): Observable<User> {
        return Observable.fromIterable(mUser)
    }

    /*
    *Last emit only the last item (or the last item that meets some condition) emitted by an Observable
    * http://reactivex.io/documentation/operators/last.html
    * */
    fun lastOperator(): Observable<User>{
        return Observable.fromIterable(mUser)
    }



}