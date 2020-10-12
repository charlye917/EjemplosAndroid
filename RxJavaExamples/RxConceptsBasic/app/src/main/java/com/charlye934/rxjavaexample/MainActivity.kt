package com.charlye934.rxjavaexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    companion object{
        const val TAG = "MainActivity"
    }

    val operation = Operations()

    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /*operation.createOperator()
            .subscribe(
                {
                    Log.d(MainActivity.TAG, "Oncomplete: $it")
                    //getLocation()
                },
                {
                    Log.d(MainActivity.TAG, "onError: $it")
                },{
                    Log.d(MainActivity.TAG, "onComplete")
                })*/

        /*operation.filterOperator()
            .filter{
                it.age >= 18
            }
            .subscribe(
                {
                    Log.d(MainActivity.TAG, "Oncomplete: $it")
                    //getLocation()
                },
                {
                    Log.d(MainActivity.TAG, "onError: $it")
                },{
                    Log.d(MainActivity.TAG, "onComplete")
                }
            )*/

        /*operation.lastOperator()
            .last(User(1,"demo1",15))
            .subscribe(
                {
                    Log.d(MainActivity.TAG, "Oncomplete: $it")
                    //getLocation()
                },
                {
                    Log.d(MainActivity.TAG, "onError: $it")
                }
            )*/

        /*operation.distinctOperator()
            //.distinct{ it.age }
            .distinct()
            .subscribe({
                Log.d(MainActivity.TAG, "Oncomplete: $it")
            },{
                Log.d(MainActivity.TAG, "onError: $it")
            })*/

        /*operation.skipOperator()
            .skip(3)
            //.distinct()
            //.skipLast(2)
            .subscribe({
                Log.d(MainActivity.TAG, "Onnext: $it")
            },{
                Log.d(MainActivity.TAG, "OnError: $it")
            },{
                Log.d(MainActivity.TAG, "Oncomplete")
            })*/

        /*operation.bufferOperator()
            .buffer(2)
            .subscribe({
                Log.d(TAG, "onNext: $it")
            },{
                Log.d(TAG, "onError: $it")
            },{
                Log.d(TAG, "onComplete")
            })*/

        /*operation.mapOperator()
            .map{
                User(it.id, it.name, it.age,"http://image:${it.name}${it.age}")
            }
            .subscribe({
                Log.d(TAG,"onNext: $it")
            },{
                Log.d(TAG,"onError: $it")
            })*/

        /*operation.flatMapOperator()
            .flatMap{
                operation.getUser(it.id)
            }
            .subscribe({
                Log.d(TAG,"onNext: $it")
            },{
                Log.d(TAG,"onError: $it")
            })

        operation.flatMapTwo()
            .flatMap{
                Observable.fromIterable(it)
            }
            .subscribe({
                Log.d(TAG,"onNext: $it")
            },{
                Log.d(TAG,"onNext: $it")
            })*/

        /*operation.groupByOperator()
            .groupBy {
                it.age
            }
            //.filter{
            //    it.key == 16 }
            .flatMapSingle {
                it.toList()
            }
            .subscribe({group ->
                /*group.subscribe(
                    {
                        Log.d(TAG,"Key: : ${group.key} | value: $it")
                    },{
                        Log.d(TAG,"onError: $it")
                    }
                )*/
                Log.d(TAG, "onNext $group")
            },{
                Log.d(TAG,"onError: $it")
            })*/

        /*operation.mergeOperator()
            .subscribe({
                Log.d(TAG,"onNext: $it")
            },{
                Log.d(TAG,"onError: $it")
            },{
                Log.d(TAG,"onComplete")
            })*/

        /*operation.concatOperator()
            .subscribe({
                Log.d(TAG,"onNext: $it")
            },{
                Log.d(TAG,"onError: $it")
            },{
                Log.d(TAG,"onComplete")
            })*/

        /*operation.startWithOperator()
            .subscribe({
                Log.d(TAG,"onNext: $it")
            },{
                Log.d(TAG,"onError: $it")
            },{
                Log.d(TAG,"onComplete")
            })*/

        /*operation.zipOperator()
            .subscribe({
                Log.d(TAG,"onNext: $it")
            },{
                Log.d(TAG,"onError: $it")
            },{
                Log.d(TAG,"onComplete")
            })*/

        createSingleObservable().subscribe(observerSingleObservable())

    }

    private fun getLocation(){
        Log.d(TAG, "latitude: 12312.12312,  Longitud: 424.23423")
    }
}
