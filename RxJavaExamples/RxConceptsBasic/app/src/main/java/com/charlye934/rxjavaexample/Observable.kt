package com.charlye934.rxjavaexample

import android.util.Log
import com.charlye934.rxjavaexample.MainActivity.Companion.TAG
import com.charlye934.rxjavaexample.data.Blog
import com.charlye934.rxjavaexample.data.User
import io.reactivex.*
import io.reactivex.disposables.Disposable
import java.lang.Exception
import kotlin.math.log

/*Types of Observables in RXJAVA:
*OBSERVABLE
*FLOWABLE
* SINGLE
* MAYBE
* COMPLETABLE
* */

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

val TAGPRUEBA = "pruebas"

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
/*
* SINGLE IS USED WHEN THE OBSERVABLE HAS TO EMIT ONLY ONE VALUE LIKE A RESPONSE FROM NETWORK
* CALL. THIS IS THE MOST COMMON OBSERVABLE WE WILL BE USING IN ANDROID AS MOST OF OUR
* APPLICATION INVOLVE NETWORK CALL
* */
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

/**
 * MAYBE IS USED WHEN THE OBSERVABLE HAS TO EMIT A VALUE OR NO VALUE.
 * IT IS NOT RECOMMENDED NO TO USE MAYBE IN RXJAVA FOR ANDROID APPLICATION
 */
fun createMaybeObservable(): Maybe<List<User>>{
    return Maybe.just(mUser)
}

fun observereMaybeObservable(): MaybeObserver<List<User>>{
    return object : MaybeObserver<List<User>>{
        override fun onSubscribe(d: Disposable) {
            Log.d(TAGPRUEBA, "onSubscribe: $d")
        }

        override fun onSuccess(t: List<User>) {
            Log.d(TAGPRUEBA, "onSuccess: $t")
        }

        override fun onError(e: Throwable) {
            Log.d(TAGPRUEBA, "onError: $e")
        }

        override fun onComplete() {
            Log.d(TAGPRUEBA, "onError")
        }

    }
}

/*
* COMPLETABLE IS USED WHEN THE OBSERVABLE HAS TO DO SOME TASK WITHOUT EMITTING A VALUE
* */
fun createCompletableObservable(): Completable{
    return Completable.create { emmiter ->
        try {
            if(!emmiter.isDisposed){
                getLocation()
                emmiter.onComplete()
            }
        }catch (e:Exception){
            emmiter.onError(e)
        }
    }
}

fun observerCompletableObservable(): CompletableObserver{
    return object : CompletableObserver{
        override fun onSubscribe(d: Disposable) {
            Log.d(TAGPRUEBA, "onSubscribe: $d")
        }

        override fun onComplete() {
            Log.d(TAGPRUEBA, "onComplete")
        }

        override fun onError(e: Throwable) {
            Log.d(TAGPRUEBA, "onError: $e")
        }

    }
}

/*
*Flowable is similar to Observabel but this comes into picture when observable is emitting a
* huge number of valeus that can't be received/consumed by the observer
* In this case, the Observable needs to skip some values on the basis of stragetgy or else ti will
* throw an exception
*
* Here, the flowable observable makes sense because it handles this exception with a strategy
*
* This stragegy is called BackPressureStrategy and this exception is called MissingBackPressureException
* An Observable for flowable is same as the observer for observable
* */
fun createflowableObservable(): Flowable<Int>{
    return  Flowable.range(1,100)
}

fun getLocation(){
    Log.d(TAG, "latitude: 12312.12312,  Longitud: 424.23423")
}


