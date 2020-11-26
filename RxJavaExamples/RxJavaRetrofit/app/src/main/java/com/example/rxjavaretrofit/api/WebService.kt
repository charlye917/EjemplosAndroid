package com.example.rxjavaretrofit.api

import com.example.rxjavaretrofit.model.Contributor
import com.example.rxjavaretrofit.model.GithubRepo
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path

object WebService {
    private lateinit var instance: WebService
    private val BASE_URL = "https://api.github.com/"
    private val logginInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val httpClientBuilder = OkHttpClient.Builder().addInterceptor(logginInterceptor)

    private val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(httpClientBuilder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    fun createService() = retrofit.create(WebServiceApi::class.java)
}