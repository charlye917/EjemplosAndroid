package com.example.rxjavaretrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavaretrofit.adapter.ContributorAdapter
import com.example.rxjavaretrofit.api.WebService
import com.example.rxjavaretrofit.model.Contributor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_retrofit_anidado.*

class RxRetrofitAnidadoActivity : AppCompatActivity() {

    private lateinit var compositeDisposable: CompositeDisposable
    private lateinit var contributors: List<Contributor>
    private lateinit var adapterContributor: ContributorAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_retrofit_anidado)

        setUpView()
        //peticionesAnidadasServidorRx()
        peticionesAnidadasServidorRxConLambdaMejoras()
    }

    private fun peticionesAnidadasServidorRxConLambdaMejoras() {
        compositeDisposable.add(WebService
            .createService()
            .getReposForUserRx("JakeWharton")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMapIterable { e -> e }
            .flatMap { e ->
                WebService
                    .createService()
                    .getReposContriForUserRx("JakeWharton", e.name)
                    .subscribeOn(Schedulers.io())
            }
            .flatMapIterable { e -> e }
            .filter { it.contributor >= 300 }
            .distinct { it.login }
            .sorted { a, b -> b.contributor - a.contributor }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { e ->
                    contributors = arrayListOf(e)
                    adapterContributor.setData(contributors)
                }
            ) { error -> error.message?.let { Log.d("TAG1", it) } }
        )
    }

    private fun peticionesAnidadasServidorRx(){
        compositeDisposable.add(WebService
            .createService()
            .getReposForUserRx("JakeWharton")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .toObservable()
            .flatMapIterable { it }
            .flatMap {
                WebService
                    .createService()
                    .getReposContriForUserRx("JakeWharton", it.name)
                    .subscribeOn(Schedulers.io())
            }
            .subscribe({
                contributors = it
                adapterContributor.setData(contributors)
            }, {
                Log.d("__TAG1", "ERROR: $it")
            })
        )
    }


    private fun setUpView(){
        compositeDisposable = CompositeDisposable()
        contributors = arrayListOf()
        adapterContributor = ContributorAdapter()
        val lim = LinearLayoutManager(this)
        lim.orientation = LinearLayoutManager.VERTICAL
        recyclerView.apply {
            layoutManager = lim
            adapter = adapterContributor
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}