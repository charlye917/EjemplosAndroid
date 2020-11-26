package com.example.rxjavaretrofit.api

import com.example.rxjavaretrofit.model.Contributor
import com.example.rxjavaretrofit.model.GithubRepo
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface WebServiceApi {

    //Sin RX
    @GET("/users/{user}/repos")
    fun getReposForUser(@Path("user") user:String): Call<List<GithubRepo>>

    //Con RX
    @GET("/users/{user}/repos")
    fun getReposForUserRx(@Path("user") user:String): Single<List<GithubRepo>>

    //Con RX
    @GET("/repos/{user}/{repo}/contributors")
    fun getReposContriForUserRx(@Path("user") user:String, @Path("repo") repo:String): Observable<List<Contributor>>
}