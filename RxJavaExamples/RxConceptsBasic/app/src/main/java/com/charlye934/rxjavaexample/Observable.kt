package com.charlye934.rxjavaexample

import android.util.Log
import com.charlye934.rxjavaexample.MainActivity.Companion.TAG
import com.charlye934.rxjavaexample.data.Blog
import com.charlye934.rxjavaexample.data.User
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import java.lang.Exception
import kotlin.math.log

val mUser = mutableListOf<User>(
    User(1,"demo1",15),
    User(2,"demo2",16),
    User(3,"demo3",17),
    User(4,"demo4",18),
    User(5,"demo5",19),
    User(6,"demo6",20),
    User(2,"demo2",16),
    User(3,"demo3",17),
    User(4,"demo4",18),
    User(5,"demo5",19),
    User(6,"demo5",20)
)

fun createObservable(): Observable<Int>{
    return Observable.create{ emitter ->
        try {
            if(!emitter.isDisposed){
                for(i in 0..100){
                    emitter.onNext(i)
                }
                emitter.onComplete()
            }
        }catch (e: Exception){
            emitter.onError(e)
        }
    }
}

fun observer(): Observer<Int> {
    return object : Observer<Int>{
        override fun onSubscribe(d: Disposable) {
            Log.d("PRUEBA", "onSubscibe: $d")
        }

        override fun onNext(t: Int) {
            Log.d("PRUEBA", "onNext: $t")
        }

        override fun onError(e: Throwable) {
            Log.d("PRUEBA", "onError: $e")
        }

        override fun onComplete() {
            Log.d("PRUEBA", "onComplete")
        }

    }
}

fun createSingleObservable(): Single<List<User>>{
    return Single.create{ emitter ->
        try {
            if(!emitter.isDisposed){
                emitter.onSuccess(mUser)
            }
        }catch (e: Exception){
            emitter.onError(e)
        }
    }
}

fun observerSingleObservable(): SingleObserver<List<User>> {
    return object : SingleObserver<List<User>>{
        override fun onSubscribe(d: Disposable) {
            Log.d("Prueba","onSubscribe: $d")
        }

        override fun onError(e: Throwable) {
            Log.d("Prueba","onError: $e")
        }

        override fun onSuccess(t: List<User>) {
            t?.forEach {
                Log.d("Prueba","onSuccess: $it")
            }
        }
    }
}