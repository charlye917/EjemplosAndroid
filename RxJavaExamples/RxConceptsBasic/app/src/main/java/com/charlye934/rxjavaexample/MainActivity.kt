package com.charlye934.rxjavaexample

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

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

        operation.distinctOperator()
            //.distinct{ it.age }
            .distinct()
            .subscribe({
                Log.d(MainActivity.TAG, "Oncomplete: $it")
            },{
                Log.d(MainActivity.TAG, "onError: $it")
            })
    }

    private fun getLocation(){
        Log.d(TAG, "latitude: 12312.12312,  Longitud: 424.23423")
    }
}
