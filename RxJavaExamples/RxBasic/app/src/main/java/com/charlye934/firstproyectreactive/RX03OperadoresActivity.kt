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
        //probarCreateLargaDuracion()
        //probarMap()
        //probarFlatMap()
        //probarScan()
        //probarWindow()
        probarElementAt()
    }

    private fun probarElementAt(){
        Log.d("TAG1", "----------------ElementAt----------------")
        val numeroObservable = Observable.just(1,2,3,4,5,6,7,8,9,)
        numeroObservable
            .elementAt(7)
            .subscribe({
               Log.d("TAG1","OnNext: $it")
            },{})
    }

    private fun probarDistinct(){
        Log.d("TAG1", "----------------Distinct----------------")
        val numeroObservable = Observable.just(1, 2, 3, 2, 5, 5, 1, 3, 9)
        numeroObservable
            .distinct()
            .subscribe({
                Log.d("TAG1", "onNExt: $it")
            }, {

            })
    }

    /*private fun probarDebounce() {
        Log.d("TAG1", "----------------Debounce----------------")
        val observable = RxTe.textChanges(etQuery)
            .debounce(500, TimeUnit.MILLISECONDS)
            .map { e -> e.toString() }
            .subscribe { e ->
                Log.d("TAG1", "onNext - String de Busqueda: $e")
                tvQuery.setText("Query: $e")
            } as Observable<String>
    }*/

    private fun probarWindow(){
        Log.d("TAG1", "----------------Window----------------")
        val observableObservable = Observable
            .range(1, 150)
            .window(3)
        observableObservable
            .subscribe({
                Log.d("TAG1", "siguiente ventana")
                it.subscribe({
                    Log.d("TAG1", "item en ventana")
                }, {
                    Log.d("TAG1", "ERROR")
                })
            }, {
                Log.d("TAG1", "ERROR")
            })
    }

    private fun probarScan(){
        Log.d("TAG1", "----------------Scan----------------")
        Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
            .scan { p0, p1 -> p0 + p1 }
            .subscribe({
                Log.d("TAG1", "onNext: $it")
            }, {
                Log.d("TAG1", "onError: $it")
            })
    }

    private fun probarGroupBy(){
        Log.d("TAG1", "----------------GroupBy----------------")
        val numerosObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
        val groupObservableObservable = numerosObservable
            .groupBy {
                if(it%2 == 0)
                    return@groupBy "PAR"
                else
                    return@groupBy "IMPAR"
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

        groupObservableObservable
            .subscribe({
                if (it.key.equals("PAR"))
                    Log.d("TAG1", "es Par: $it")
                else
                    Log.d("TAG1", "es Impar: $it")
            }, {
                Log.d("TAG1", "onError: $it")
            })

    }

    private fun probarFlatMap(){
        Log.d("TAG1", "----------------FlatMap----------------")
        Observable
            .just("item2")
            .flatMap {
                Log.d("TAG1", "inside the flapMat$it")
                return@flatMap Observable.just("$it 1, $it 2, $it 3")
            }
            .subscribe({
                Log.d("TAG1", it!!)
            }, {

            })
    }

    private fun probarMap(){
        Log.d("TAG1", "----------------Map----------------")
        val empleados = Empleado.setUpEmpleados()
        Observable.fromArray(empleados)
            .map{
                var nombres = ArrayList<String>()
                for(e in empleados){
                    nombres.add(e.nombre)
                }
                return@map nombres
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG1", "Map->items: $it")

            }, {
                Log.d("TAG1", "Error: $it")
            })
    }

    private fun probarLambada(){
        /*
        (argumentos)->{cuerpo o body}
        (art1, arg2)->{body}
         */
    }


    var sumar: Sumar = object : Sumar {
        override fun apply(a: Int, b: Int): Int {
            return a + b
        }
    }


    private fun largaDuracion(): String{
        try{
            Thread.sleep(10000)
        }catch (e: InterruptedException){
            e.printStackTrace()
        }
        Log.d("Tag1", "terminado")
        return "Terminado"
    }

    private fun probarCreateLargaDuracion(){
        Log.d("TAG1", "----------------Create LargaDuracion----------------")
        Observable
            .create(ObservableOnSubscribe<String> {
                try {
                    it.onNext(largaDuracion())
                } catch (e: java.lang.Exception) {
                    it.onError(e)
                }
            })
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                Log.d("TAG1", "onNext: $it")
            }, {
                Log.d("TAG1", "onError: " + it.message)
            })
        //largaDuracion()
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
