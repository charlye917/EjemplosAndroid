package com.charlye934.rxjavaexample

import android.annotation.SuppressLint
import android.util.Log
import com.charlye934.rxjavaexample.data.Blog
import com.charlye934.rxjavaexample.data.User
import com.charlye934.rxjavaexample.data.UserProfile
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.lang.Exception
import java.util.concurrent.TimeUnit
import io.reactivex.functions.BiFunction as BiFunction

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
        User(6,"demo6",20),
        User(2,"demo2",16),
        User(3,"demo3",17),
        User(4,"demo4",18),
        User(5,"demo5",19),
        User(6,"demo5",20)
    )
    val mUserProfile =  mutableListOf<UserProfile>(
        UserProfile(1,"demo1",15,"http://test.com/1"),
        UserProfile(2,"demo2",16,"http://test.com/2"),
        UserProfile(3,"demo3",17,"http://test.com/3"),
        UserProfile(4,"demo4",18,"http://test.com/4"),
        UserProfile(5,"demo5",19,"http://test.com/5"),
        UserProfile(6,"demo6",20,"http://test.com/6"),
        UserProfile(2,"demo2",16,"http://test.com/7"),
        UserProfile(3,"demo3",17,"http://test.com/8"),
        UserProfile(4,"demo4",18,"http://test.com/9"),
        UserProfile(5,"demo5",19,"http://test.com/10"),
        UserProfile(6,"demo5",20,"http://test.com/11")

    )
    val mBlogList = mutableListOf<Blog>(
        Blog(1,1,"title1","conent1"),
        Blog(2,1,"title2","conent2"),
        Blog(3,2,"title1","conent1"),
        Blog(4,2,"title2","conent2"),
        Blog(5,2,"title3","conent3"),
        Blog(6,3,"title1","conent1"),
        Blog(7,3,"title1","conent1")
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
    * filter emit only those items from an Observable that pass a predicate test
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

    /*
    * Distinct suppress duplicate items emitted by an Observable
    * */
    fun distinctOperator(): Observable<User>{
        return Observable.fromIterable(mUser)
    }

    /*
    * Skip suppress the first n items emitted by an observable
    * */
    fun skipOperator(): Observable<User>{
        return Observable.fromIterable(mUser)
    }

    /*
    * Buffer periodically gather items emitted by an observable into bundles
    * and emited these bundles rather than emitting the items one at a time
    * http://reactivex.io/documentation/operators/buffer.html
    * */
    fun bufferOperator(): Observable<User>{
        return Observable.fromIterable(mUser)
    }

    /*
    * Map
    * transform the items emitted by an observable by applying a function to each item
    * */
    fun mapOperator(): Observable<User>{
        return Observable.fromIterable(mUser)
    }

    /*
    * flatMap transform the items emitted by an Observable into Observables
    * then flatten the emissions from those into a single Observable
    * */
    fun flatMapOperator(): Observable<User>{
        return Observable.fromIterable(mUser)
    }

    fun getUser(id:Long):Observable<User>{
        return Observable.fromIterable(mUser)
            .filter {
                it.id == id
            }
    }

    fun flatMapTwo(): Observable<List<User>>{
        return Observable.just(mUser)
    }

    /*
    *Group by divide an Observable into a set of Observables that each emit
    * a different subset of items from the original observable
    * */
    fun groupByOperator(): Observable<User>{
        return Observable.fromIterable(mUser)
    }

    /*
    * Merge combine multiple observables into one by merging their emissions
    * */
    fun getUser(): Observable<User>{
        return Observable.fromIterable(mUser)
    }

    fun getProfile(): Observable<UserProfile>{
        return Observable.fromIterable(mUserProfile)
    }

    fun mergeOperator(): Observable<Any>{
        return Observable.merge(getUser(), getProfile())
    }

    /*
    * Concat emit the emissions from two or more Observables without intervaling them
    * */
    fun getNum1To100(): Observable<Int>{
        return Observable.range(1, 100)
    }

    fun getNum101To150(): Observable<Int>{
        return Observable.range(101, 150)
    }

    fun concatOperator(): Observable<Int>{
        return getNum1To100().concatWith(getNum101To150())
    }

    /*
    * StartWith emit a specified sequence of items before beginning to emit the items from the source obsevable
    * */
    fun startWithOperator(): Observable<Int>{
        return getNum101To150().startWith(getNum1To100())
    }

    /*
    * Zip combine the emissions of multiple observables together via a specified function and emit
    * single items for each combination based on the results of this function
    * */

    fun getBlogs(): Observable<List<Blog>>{
        return Observable.just(mBlogList)
    }

    fun getUsers(): Observable<List<User>>{
        return Observable.just(mUser)
    }

    @SuppressLint("CheckResult")
    fun zipOperator():Observable<Any>{
        val num = Observable.just(1,2,3,4,5)
        val char = Observable.just("A","B","C","D")

        return Observable.zip(num, char, BiFunction(){t1, t2 ->
            "$t1$t2"
        })
    }

}