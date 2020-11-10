package com.charlye934.firstproyectreactive

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.ReplaySubject

class RX05SubjectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx05_subject)
    }

    private fun replaySubjectExample(){
        Log.d("TAG1", "----------------ReplaySubject----------------")
        val source = ReplaySubject.create<String>()
        val primerObserver = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: String) {
                Log.d("TAG1", "Primer Observer onNext value: $t")
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {
                Log.d("TAG1", "Primer Observer onComplete")
            }
        }
        val segundoObserver = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: String) {
                Log.d("TAG1", "Segundo Observer onNext value: $s")
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {}
        }

        source.subscribe(primerObserver)
        source.onNext("A")
        source.onNext("l")
        source.onNext("b")
        source.subscribe(segundoObserver)
        source.onNext("e")
        source.onNext("r")
        source.onNext("t")
        source.onNext("o")
        source.onComplete()
    }

    private fun publishSubjectExample(){
        Log.d("TAG1", "----------------PublishSubject----------------")
        val source = PublishSubject.create<String>()
        val primerObserver = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: String) {
                Log.d("TAG1", "Primer Observer onNext value: $t")
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {
                Log.d("TAG1", "Primer Observer onComplete")
            }
        }

        val segundoObserver = object : Observer<String>{
            override fun onSubscribe(d: Disposable) {}
            override fun onNext(t: String) {
                Log.d("TAG1", "Segundo Observer onNext value: $s")
            }
            override fun onError(e: Throwable) {}
            override fun onComplete() {
                Log.d("TAG1", "Segundo Observer onComplete")
            }
        }

        source.subscribe(primerObserver)
        source.onNext("A")
        source.onNext("l")
        source.onNext("b")
        source.subscribe(segundoObserver)
        source.onNext("e")
        source.onNext("r")
        source.onNext("t")
        source.onNext("o")
        source.onComplete()
    }
}