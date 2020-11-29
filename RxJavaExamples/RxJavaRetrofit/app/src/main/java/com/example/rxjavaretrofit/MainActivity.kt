package com.example.rxjavaretrofit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaretrofit.adapter.RepositoryAdapter
import com.example.rxjavaretrofit.model.GithubRepo
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpView()
    }

    private fun setUpView(){
        btRxRetrofit.setOnClickListener {
            startActivity(Intent(this, RxRetrofitActivity::class.java))
        }

        btRxRetrofitAnidado.setOnClickListener {
            startActivity(Intent(this, RxRetrofitAnidadoActivity::class.java))
        }
    }
}