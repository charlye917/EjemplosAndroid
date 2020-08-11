package com.charlye934.loginddagger.http

import com.charlye934.loginddagger.http.pojo.Twitch
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers


interface TwitchAPI {

    @GET("games/top")
    @Headers("Accept: application/vnd.twitchtv.v5+json")
    fun getTopGames(@Header("Client-ID")  clientID:String): Call<Twitch>

   @GET("games/top")
   @Headers("Accept: application/vnd.twitchtv.v5+json")
   fun getTopGamesObservable(@Header("Client-ID") clientID: String):Observable<Twitch>
}