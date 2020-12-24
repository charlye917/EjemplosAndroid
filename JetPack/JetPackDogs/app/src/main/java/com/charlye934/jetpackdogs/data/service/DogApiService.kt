package com.charlye934.jetpackdogs.data.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DogApiService{
    private val BASE_URL = "https://raw.githubusercontent.com"
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(DogApi::class.java)

    suspend fun getDogs() = retrofit.getDogs()
}