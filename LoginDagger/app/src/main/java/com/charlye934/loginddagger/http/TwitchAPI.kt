package com.charlye934.loginddagger.http

import com.charlye934.loginddagger.http.twitch.Twitch
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface TwitchAPI {
    @GET("games/top")
    fun getTopGames(@Header("Client-ID")  clientID:String): Call<Twitch>
}