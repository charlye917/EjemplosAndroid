package com.example.rxjavaretrofit

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rxjavaretrofit.adapter.RepositoryAdapter
import com.example.rxjavaretrofit.api.WebService
import com.example.rxjavaretrofit.model.GithubRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_rx_retrofit.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.function.Consumer

class RxRetrofitActivity : AppCompatActivity() {

    private lateinit var adapter: RepositoryAdapter
    private lateinit var githubRepos: List<GithubRepo>
    private lateinit var compositeDisposable: CompositeDisposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rx_retrofit)

        setUpView()
        //sinRxJava()
        conRxLambda()
    }

    private fun conRx(){
        compositeDisposable.add(
            WebService
                .createService()
                .getReposForUserRx("JakeWharton")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(@RequiresApi(Build.VERSION_CODES.N)
                object : Consum er<List<GithubRepo>>(){

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
                        adapter.setData(it)
                    },{
                        Log.d("TAG1","Error: ${it.printStackTrace()}")
                    }
                )
        )
    }

    private fun sinRxJava(){
        val call = WebService
            .createService()
            .getReposForUser("JakeWharton")

        call.enqueue(object : Callback<List<GithubRepo>>{
            override fun onResponse(
                call: Call<List<GithubRepo>>,
                response: Response<List<GithubRepo>>
            ) {
                githubRepos = response.body()!!
                adapter.setData(githubRepos)
            }

            override fun onFailure(call: Call<List<GithubRepo>>, t: Throwable) {
                Log.d("TAG1","ERROR ${t.printStackTrace()}")
            }

        })
    }

    private fun setUpView(){
        compositeDisposable = CompositeDisposable()
        githubRepos = listOf()
        adapter = RepositoryAdapter()
        val lim = LinearLayoutManager(this)
        lim.orientation = LinearLayoutManager.VERTICAL
        recyclerView.apply {
            layoutManager = lim
            adapter = this.adapter
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}