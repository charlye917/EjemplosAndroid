package com.charlye934.jetpackdogs.model.service

import com.charlye934.jetpackdogs.model.db.DogBreed
import com.google.gson.internal.GsonBuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

object DogsApiService{
        private val BASE_URL = "https://raw.githubusercontent.com"

        fun getRetrofitInstance(): Retrofit {
            return Retrofit
                .Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
}