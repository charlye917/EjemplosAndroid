package com.charlye934.firstproyectreactive

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.schedulers.Timed
import io.reactivex.subjects.Subject
import java.util.concurrent.TimeUnit
import java.util.function.BiFunction

class  RX03OperadoresActivity : AppCompatActivity() {
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

    @SuppressLint("CheckResult")
    private fun probarReduce(){
        Log.d("TAG1", "----------------Reduce----------------")
        Observable.just(2, 2, 2, 2)
            .reduce(@RequiresApi(Build.VERSION_CODES.N)
            object : BiFunction<Int, Int, Int>, io.reactivex.functions.BiFunction<Int, Int, Int> {
                @SuppressLint("CheckResult")
                override fun apply(p0: Int, p1: Int): Int {
                    return p0 * p1
                }
            })
            .subscribe{
                Log.d("TAG1", "Resultado: $it")
            }

    }

    /*private fun probarMaxMinSum(){
        Log.d("TAG1", "----------------MaxMinSum----------------")
        val observable = Observable.fromArray(1, 34, 55, 1000, -234, 33, 567)
        MathObservable
            .max(observable)
            .subscribe{
                Log.d("TAG1", "max: $it")
            }

        MathObservable
            .min(observable)
            .subscribe{
                Log.d("TAG1", "min: $it")
            }

        MathObservable
            .sumInt(observable)
            .subscribe{
                Log.d("TAG1", "min: $it")
            }

    }*/

    @SuppressLint("CheckResult")
    private fun probarCount(){
        Log.d("TAG1", "----------------Count----------------")
        val numeroObservable = Observable.fromArray(1, 34, 55, 33, 567)
        numeroObservable
            .count()
            .subscribe(
                { Log.d("TAG1", "count $it") }, {}
            )

    }

    /*@SuppressLint("CheckResult")
    private fun probarAverage(){
        Log.d("TAG1", "----------------Average----------------")
        val numeroObservable = Observable.fromArray(1, 34, 55, 33, 567)
        MathObservable.averageDouble(numeroObservable)
            .subscribe{
                Log.d("TAG1", "average")
            }
    }*/

    @SuppressLint("CheckResult")
    private fun probarTakeWhile() {
        Log.d("TAG1", "----------------TakeWhile----------------")
        Observable
            .create { emitter: ObservableEmitter<Any> ->
                for (i in 0..9) {
                    try {
                        Thread.sleep(500)
                        emitter.onNext(i)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                emitter.onComplete()
            }
            .takeWhile { e: Any -> e as Int <= 4 }
            .subscribe { e: Any ->
                Log.d(
                    "TAG1",
                    "onNext: $e"
                )
            }
    }

    private fun probarTakeUntil(){
        Log.d("TAG1", "----------------TakeUntil----------------")
        val observable1 = Observable
            .create{ emmiter: ObservableEmitter<Any> ->
                for(i in 0..10){
                    try {
                        Thread.sleep(500)
                    }catch (e: InterruptedException){
                        e.printStackTrace()
                    }
                }
                emmiter.onComplete()
            }

        val observable2 = Observable.timer(3, TimeUnit.SECONDS)

        observable1.takeUntil(observable2)
            .subscribe{Log.d("TAG1", "onNext: $it")}

    }

    private fun probarSkipWhile(){
        Log.d("TAG1", "----------------SkipWhile----------------")
        Observable
            .create { emitter: ObservableEmitter<Any> ->
                for (i in 0..9) {
                    try {
                        Thread.sleep(400)
                        emitter.onNext(i)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }
                }
                emitter.onComplete()
            }
            .skipWhile { e: Any -> e as Int <= 4 }
            .subscribe { e: Any ->
                Log.d(
                    "TAG1",
                    "onNext: $e"
                )
            }

    }

    @SuppressLint("CheckResult")
    private fun probarSkipUntil(){
        Log.d("TAG1", "----------------SkipUntil----------------")
        val observable1: Observable<Int> = Observable
            .create {
                for (i in 0..10) {
                    try {
                        Thread.sleep(500)
                        it.onNext(i)
                    }catch (e: InterruptedException){
                        e.printStackTrace()
                    }
                }
                it.onComplete()
            }

        val observable2 = Observable.timer(3, TimeUnit.SECONDS)

        observable1.skipUntil(observable2)
            .subscribe{
                Log.d("TAG1", "onNext: $it")
            }
    }

    @SuppressLint("CheckResult")
    private fun probarSeqeunceEqual(){
        Log.d("TAG1", "----------------SequenceEqual----------------")
        val numeroObservable1 = Observable.just(1, -1, 2, -6, 4, -78)
        val numeroObservable2 = Observable.just(1, -1, 2, -6, 4, -78)

        Observable.sequenceEqual(numeroObservable1, numeroObservable2)
            .subscribe({ Log.d("TAG1", "onSuccess: $it") }, {})

    }

    @SuppressLint("CheckResult")
    private fun probarDefaultIfEmpty(){
        Log.d("TAG1", "----------------DefaultIfEmpty----------------")
        Observable.create(ObservableOnSubscribe<Int> { emitter ->
            val num = 100
            if (num % 2 == 0) {
                emitter.onNext(num)
            }
            emitter.onComplete()
        })
            .defaultIfEmpty(0)
            .subscribe { Log.d("TAG1", "onNext: $it") }
    }

    @SuppressLint("CheckResult")
    private fun probarContains(){
        Log.d("TAG1", "----------------Contains----------------")
        val numeroObservable = Observable.just(2, 0, -2, 66, 100, -478)
        numeroObservable
            .contains(-477)
            .subscribe({
                Log.d("TAG1", "onSuccess: $it")
            }, {})

    }

    private fun probarAmb(){
        Log.d("TAG1", "----------------Amb----------------")
        val numeroObservable1 = Observable.just(1, -1, 2, -6, 4, -78)
        val numeroObservable2 = Observable.just(2, 0, -2, 66, 100, -478)

        Observable
            .ambArray(numeroObservable1.delay(10, TimeUnit.SECONDS), numeroObservable2)
            .subscribe{
                Log.d("TAG1", "onNext: $it")
            }

    }

    @SuppressLint("CheckResult")
    private fun probarAll(){
        Log.d("TAG1", "----------------All----------------")
        val numeroObservable = Observable.just(1, -1, 2, -6, 4, -78)
        numeroObservable
            .all { it > 0 }
            .subscribe({
                Log.d("TAG1", "onSuccess: $it")
            }, {})
    }

    @SuppressLint("CheckResult")
    private fun probarUsing() {
        Log.d("TAG1", "----------------Using----------------")
        Observable.using(
            { "Using" },
            { s ->
                Observable.create { e: ObservableEmitter<Char?> ->
                    for (c in s.toCharArray()) {
                        e.onNext(c)
                    }
                    e.onComplete()
                }
            })
        { Log.d("TAG1", "Disposase: $it") }
            .subscribe {
                Log.d(
                    "TAG1",
                    "onNext $it"
                )
            }
    }

    private fun probarTimeStamp(){
        Log.d("TAG1", "----------------TimeStamp----------------")
        val letrasObservable: Observable<String> = Observable.create {
            it.onNext("A")
            it.onNext("B")
            it.onNext("C")
        }
        letrasObservable
            .timestamp()
            .subscribe(object : Observer<Timed<String>> {
                override fun onSubscribe(d: Disposable) {}
                override fun onNext(t: Timed<String>) {
                    Log.d("TAG1", "onNext $t")
                }

                override fun onError(e: Throwable) {}
                override fun onComplete() {}

            })
    }

    @SuppressLint("CheckResult")
    private fun probarTimerOut(){
        Log.d("TAG1", "----------------TimeOut----------------")
        val letrasObservable: Observable<String> = Observable.create{
            it.onNext("A")
            it.onNext("B")
            it.onNext("C")
        }

        Observable.timer(1, TimeUnit.SECONDS)
            .timeout(500, TimeUnit.MILLISECONDS)
            .subscribe(
                { e: Long ->
                    Log.d(
                        "TAG1",
                        "onNext $e"
                    )
                }
            ) { e: Throwable ->
                Log.d(
                    "TAG1",
                    "onError $e"
                )
            }

    }

    private fun probarTimeInterval() {
        Log.d("TAG1", "----------------TimeInterval----------------")
        val letrasObservable = Observable.create { e: ObservableEmitter<String?> ->
            e.onNext("A")
            e.onNext("B")
            e.onNext("C")
        }
        Observable//TODO Debe de ir letrasObsercables
            .interval(500, TimeUnit.MILLISECONDS)
            .take(3)
            .timeInterval()
            .subscribe(object : Subject<Timed<Long?>?>() {
                override fun hasObservers(): Boolean {
                    return false
                }

                override fun hasThrowable(): Boolean {
                    return false
                }

                override fun hasComplete(): Boolean {
                    return false
                }

                override fun getThrowable(): Throwable? {
                    return null
                }

                override fun onSubscribe(d: Disposable) {}
                override fun onNext(longTimed: Timed<Long?>) {
                    Log.d("TAG1", "onNext: $longTimed")
                }

                override fun onError(e: Throwable) {}
                override fun onComplete() {}
                override fun subscribeActual(observer: Observer<in Timed<Long?>?>?) {}
            })
    }

    private fun probarObserverOnSubscribe(){
        Log.d("TAG1", "----------------ObserverOn-SubscribeOn----------------")
        val observable: Observable<String> = Observable.create{
            Log.d("TAG1", "observable ejecutandose en hilo: ${Thread.currentThread().name}")
            it.onNext("Emitiendo item")
        }

        observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                Log.d("TAG1", "observer ejecutandose en: ${Thread.currentThread().name}")
            }

    }

    @SuppressLint("CheckResult")
    private fun probarDo(){
        Log.d("TAG1", "----------------Do----------------")
        val numeroObservable = Observable.just("1", "4", "89", "45", "0")
        numeroObservable
            .doOnNext{Log.d("TAG1", "doOnNext: $it")}
            .doAfterNext{Log.d("TAG1", "doAfterNext: $it")}
            .doOnComplete { Log.d("TAG1", "doOnComplete") }
            .subscribe{
                Log.d("TAG1", "oNext")
            }
    }

    @SuppressLint("CheckResult")
    private fun probarDealy(){
        Log.d("TAG1", "----------------Delay----------------")
        val numeroObservable = Observable.just("1", "4", "89", "45", "0")
        numeroObservable
            .delay(5, TimeUnit.SECONDS)
            .subscribe{
                Log.d("TAG1", "onNext: $it")
            }
    }

    @SuppressLint("CheckResult")
    private fun probarRetryWhen(){
        Observable
            .create { e: ObservableEmitter<Any> ->
                e.onNext("probando Retry")
                e.onError(Throwable("test"))
            }
            .retryWhen { error: Observable<Throwable?> -> error.retry() }
            .subscribe(
                { e: Any ->
                    Log.d(
                        "TAG1",
                        "onNext $e"
                    )
                },
                { e: Throwable ->
                    Log.d(
                        "TAG1",
                        "onError$e"
                    )
                }
            ) { Log.d("TAG1", "onComplete") }
    }

    private fun probarZip(){
        Log.d("TAG1", "----------------Zip----------------")
        val observable1 = Observable
            .interval(1, TimeUnit.SECONDS)
            .map { "Grupo1: $it" }

        val observable2 = Observable
            .interval(1, TimeUnit.SECONDS)
            .map { "Grupo2: $it" }

        Observable.zip(observable1, observable2, { x, y -> "$x - $y" })
            .subscribe { Log.d("TAG1", it) }
    }

    private fun probarMerge(){
        Log.d("TAG1", "----------------Merge----------------")
        val observable1 = Observable
            .interval(2, TimeUnit.SECONDS)
            .map { "Grupo 1 : $it" }

        val observable2 = Observable
            .interval(1, TimeUnit.SECONDS)
            .map { "Grupo 2: $it" }

        Observable.merge(observable1, observable2)
            .subscribe{Log.d("TAG1", "onNext: $it")}

    }

    private fun probarJoin(){
        Log.d("TAG1", "----------------Join----------------")
        val LEFTWINDOWDURATION = 0L
        val RIGHTWINDOWDURATION = 0L

        val left = Observable
            .interval(70, TimeUnit.MILLISECONDS).take(10)

        val right = Observable
            .interval(100, TimeUnit.MILLISECONDS).take(10)

        left.join(
            right,
            { e -> Observable.timer(LEFTWINDOWDURATION, TimeUnit.MILLISECONDS) },
            { e -> Observable.timer(RIGHTWINDOWDURATION, TimeUnit.MILLISECONDS) },
            { l, r ->
                Log.d("TAG1", "left: $l right: $r")
                l + r
            })
            .subscribe { e -> Log.d("TAG1", "result: $e") }

    }

    /*private fun probarCombineLatest(){
        Log.d("TAG1", "----------------combineLatest----------------")
        val observable1 = Observable.interval(100, TimeUnit.MILLISECONDS).take(10)
        val observable2 = Observable.interval(50, TimeUnit.MILLISECONDS).take(20)

        Observable
            .combineLatest(observable1, observable2, @RequiresApi(Build.VERSION_CODES.N)
            object : BiFunction<Long?, Long?, String?>() {
                override fun apply(p0: Long?, p1: Long?): String? {
                    return "Observable1: $p0 Observable2: $p1"
                }
            })
            .subscribe { e -> Log.d("TAG1", "onNext: $e") }
    }*/

    private fun probarTakeLast(){
        Log.d("TAG1", "----------------TakeLast----------------")
        val numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78)
        numeroObservable
            .takeLast(5)
            .subscribe{
                Log.d("TAG1", "onNext: $it")
            }
    }

    private fun probarTake(){
        Log.d("TAG1", "----------------Take----------------")
        val numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78)
        numeroObservable
            .take(5)
            .subscribe{
                Log.d("TAG1", "onNext $it")
            }

    }

    private fun probarSkipLast(){
        Log.d("TAG1", "----------------Skip Last----------------")
        val numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78)
        numeroObservable
            .skipLast(3)
            .subscribe{
                Log.d("TAG1", "onNext: $it")
            }
    }

    @SuppressLint("CheckResult")
    private fun probarSkip(){
        Log.d("TAG1", "----------------Skip----------------")
        val numeroObservable = Observable.just(1000, 2, 3, 4, 9, 7, 3, 81, 98, 78)
        numeroObservable
            .skip(3)
            .subscribe{
                Log.d("TAG1", "onNext: $it")
            }

    }

    @SuppressLint("CheckResult")
    private fun provarSample(){
        Log.d("TAG1", "----------------Sample----------------")
        Observable
            .interval(490, TimeUnit.MILLISECONDS)
            .take(10)
            .sample(2000, TimeUnit.MILLISECONDS)
            .subscribe { Log.d("TAG1", "onNext: $it") }
    }

    @SuppressLint("CheckResult")
    private fun probarLast(){
        Log.d("TAG1", "----------------Last----------------")
        val numeroObservable = Observable.just(1000, 2, 3, 4, 5, 6, 7, 8, 9)
        numeroObservable
            .last(0)
            .subscribe({
                Log.d("TAG1", "onNext: Es el ultimo: $it")
            }, {})

    }

    @SuppressLint("CheckResult")
    private fun probarIgnoreElements(){
        Log.d("TAG1", "----------------IngnoreElements----------------")
        val numeroObservable = Observable.just(1000, 2, 3, 4, 5, 6, 7, 8, 9)
        numeroObservable
            .ignoreElements()
            .subscribe{
                Log.d("TAG1", "onComplete")
            }

    }

    @SuppressLint("CheckResult")
    private fun probarFirst(){
        Log.d("TAG1", "----------------First----------------")
        val numeroObservable = Observable.just(1000, 2, 3, 4, 5, 6, 7, 8, 9)
        numeroObservable
            .first(0)
            .subscribe({
                Log.d("TAG1", "onNext: $it")
            }, {})

    }

    @SuppressLint("CheckResult")
    private fun probarFilter(){
        Log.d("TAG1", "----------------Filter----------------")
        val numeroObservable = Observable.just(1, 2, 3, 4, 5, 6, 7, 8, 9)
        numeroObservable
            .filter {
                it % 2 == 0
            }
            .subscribe{
                Log.d("TAG1", "onNext es par: $it")
            }
    }

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
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
                for (@SuppressLint("CheckResult") int in it)
                    Log.d("TAG1", "Buffer item-> $it")
            }, {
                Log.d("TAG1", "onError $it")
            })

    }

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
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

    @SuppressLint("CheckResult")
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
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: Array<String>) {
                        Log.d("TAG1", "JustArray->onNext " + t!!.size)
                    }

                    override fun onError(e: Throwable) {}
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
                    override fun onComplete() {}
                    override fun onSubscribe(d: Disposable) {}
                    override fun onNext(t: String) {
                        Log.d("TAG1", "Just->onNext $t")
                    }

                    override fun onError(e: Throwable) {
                        Log.d("TAG1", "just onError: $e")
                    }
                }
            )
    }
}
