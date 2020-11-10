package com.charlye934.firstproyectreactive

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.*
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.*

class RX04TiposObservablesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx04_tipos_observables)

        /*
        * 1.- Observable: Emite 0 o n items y termina con exito o error
        * observableObserver()
        *
        * 2.- Single: Emite un unico item o lanza error
        *singleAndSingleObserver()
        *
        * 3.-Maybe: Emite un unico item o ninguno o lanza error
        * maybeAndMaybeObserver()
        *
        * 4.-Completable: No emite items, pero finaliza con eixot o con error
        *compleatableAndCompletableObserver()
        *
        * 5.Flowable: Emite 0 o n items y termina con exito o con error
        * Ideal para cuando se emiten mas items de los que el observador puede manejar
        * lo que se conoce como BackPresure
        * */

        flowableAndObserver()
    }

    private fun flowableAndObserver() {
        Log.d("TAG1", "----------------Flowable-Observer----------------")
        val flowable = Flowable.range(1, 5)
        val singleObserver = object : SingleObserver<Int>{
            override fun onSubscribe(d: Disposable) {}
            override fun onSuccess(t: Int) {
                Log.d("TAG1", "onSuccess: $t")
            }
            override fun onError(e: Throwable) {}
        }
    }

    private fun compleatableAndCompletableObserver() {
        Log.d("TAG1", "----------------Completable-CompletableObserver----------------")
        val completable = Completable.create {
            it.onComplete()
        }

        val completableObserver = object : CompletableObserver{
            override fun onSubscribe(d: Disposable) {}
            override fun onComplete() {
                Log.d("TAG1", "onComplete: Actualizado El servidor correctamente")
            }
            override fun onError(e: Throwable) {}
        }

        completable.subscribe(completableObserver)
    }

    private fun maybeAndMaybeObserver() {
        Log.d("TAG1", "----------------Maybe-MaybeObserver----------------")
        val empleadoMaybe: Maybe<Empleado> = Maybe.create {
            val empleado = Empleado(1, "Carlos", "Android", Date(), 1000.00, 100.0)
            it.onSuccess(empleado)
        }

        val empleadoMaybeEmpty = Maybe.empty<Empleado>()
        val empleadoMaybeObserver = object : MaybeObserver<Empleado> {
            override fun onSubscribe(d: Disposable) {}
            override fun onSuccess(t: Empleado) {
                Log.d("TAG1", "onSuccess: " + t.nombre)
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {
                Log.d("TAG1", "onComplete")
            }
        }

        empleadoMaybe.subscribe(empleadoMaybeObserver)
        empleadoMaybeEmpty.subscribe(empleadoMaybeObserver)
    }

    private fun singleAndSingleObserver() {
        Log.d("TAG1", "----------------Single-SingleObserver----------------")
        val empleadoSingle: Single<Empleado> = Single.create { emitter ->
            val empleado = Empleado(1, "Carlos", "Android", Date(), 1000.00, 100.0)
            emitter.onSuccess(empleado)
        }

        val singleObserver = object : SingleObserver<Empleado>{
            override fun onSubscribe(d: Disposable) {}
            override fun onSuccess(t: Empleado) {
                Log.d("TAG1", "onSuccess: " + t.nombre)
            }
            override fun onError(e: Throwable) {}
        }
    }

    private fun observableObserver(){
        Log.d("TAG1", "----------------Observable-Observer----------------")
        val empleados = Empleado.setUpEmpleados()
        val empleadoObservable: Observable<Empleado> = Observable.create {
            for (empleado in empleados) {
                it.onNext(empleado)
            }
            it.onComplete()
        }

        val empleadoObserver: Observer<Empleado> = object : Observer<Empleado> {
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(empleado: Empleado) {
                Log.d("TAG1", "onNext: " + empleado.nombre)
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {
                Log.d("TAG1", "onComplete: ")
            }
        }

        empleadoObservable.subscribe(empleadoObserver)
    }
}