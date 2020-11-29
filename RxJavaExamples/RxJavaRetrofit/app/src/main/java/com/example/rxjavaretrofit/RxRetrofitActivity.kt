package com.example.rxjavaretrofit

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavaretrofit.adapter.RepositoryAdapter
import com.example.rxjavaretrofit.api.WebService
import com.example.rxjavaretrofit.model.GithubRepo
import hu.akarnokd.rxjava2.math.MathObservable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.math.log

class RxRetrofitActivity : AppCompatActivity() {

    private lateinit var adapterRepo: RepositoryAdapter
    private lateinit var githubRepos: MutableList<GithubRepo>
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_retrofit)

        setUpView()
        //sinRxJava()
        //conRxLambda()
        //conRxFiltrarLenguaje()
        conRxOrdenarPorEstrellas()
    }

    @SuppressLint("CheckResult")
    private fun conRxAverageEstrellas(){
        val observable = WebService
                .createService()
                .getReposForUserRx("JakeWharton")
                .toObservable()
                .flatMapIterable { it }
                .map { it.stargazers_count }

        MathObservable.averageDouble(observable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it }

        MathObservable.max(observable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it }

        MathObservable.min(observable)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { it }
    }

    private fun conRxOrdenarPorEstrellas(){
        compositeDisposable.add(
                WebService
                        .createService()
                        .getReposForUserRx("JakeWharton")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toObservable()
                        .flatMapIterable{it}
                        .toSortedList { o1, o2 ->
                            o1.stargazers_count - o2.stargazers_count
                        }
                        .subscribe({
                                   adapterRepo.setData(it)
                        },{
                            Log.d("TAG1","ERROR: $it")
                        })
        )
    }

    private fun conRxFiltrarLenguajeLambdaMasOperadores(){
        compositeDisposable.add(
                WebService
                        .createService()
                        .getReposForUserRx("JakeWharton")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toObservable()
                        .flatMapIterable { it }
                        .filter{it.language == "Java"}
                        //.take(3)
                        .skip(3)
                        .subscribe({
                            githubRepos.add(it)
                            adapterRepo.setData(githubRepos)
                        },{
                            Log.d("_TAG","error: $it")
                        })
        )
    }

    private fun conRxFiltrarLenguaje(){
        compositeDisposable.add(
                WebService
                        .createService()
                        .getReposForUserRx("JakeWharto")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .toObservable()
                        .flatMapIterable { it }
                        .filter{ it.language == "Java" }
                        .subscribe({
                            githubRepos.add(it)
                            Log.d("__TAG",githubRepos.toString())
                            adapterRepo.setData(githubRepos)
                        },{
                            Log.d("TAG1", "error: $it")
                        })
        )
    }

    private fun conRxLambda(){
        compositeDisposable.add(
                WebService
                        .createService()
                        .getReposForUserRx("JakeWharton")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                {
                                    githubRepos.addAll(it)
                                    adapterRepo.setData(githubRepos)
                                },
                                {
                                    Log.d("TAG1", "Error: ${it.printStackTrace()}")
                                }
                        )
        )
    }

    private fun sinRxJava(){
        val call = WebService
            .createService()
            .getReposForUser("JakeWharton")

        call.enqueue(object : Callback<List<GithubRepo>> {
            override fun onResponse(
                    call: Call<List<GithubRepo>>,
                    response: Response<List<GithubRepo>>
            ) {
                githubRepos.addAll(response.body()!!)
                adapterRepo.setData(githubRepos)
            }

            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                Log.d("TAG1", "ERROR ${t.printStackTrace()}")
            }

        })
    }

    private fun setUpView(){
        compositeDisposable = CompositeDisposable()
        githubRepos = mutableListOf()
        adapterRepo = RepositoryAdapter()
        val lim = LinearLayoutManager(this)
        lim.orientation = LinearLayoutManager.VERTICAL
        recyclerView.apply {
            layoutManager = lim
            adapter = adapterRepo
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}