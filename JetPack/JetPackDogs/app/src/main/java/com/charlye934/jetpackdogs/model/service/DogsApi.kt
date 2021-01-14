package com.charlye934.jetpackdogs.model.service

import com.charlye934.jetpackdogs.model.db.DogBreed
import retrofit2.http.GET

interface DogsApi {
    @GET("DevTides/DogsApi/master/dogs.json")
    suspend fun getDogs(): List<DogBreed>

    class Builder{
        fun build(): DogsApi {
            return DogsApiService
                .getRetrofitInstance()
                .create(DogsApi::class.java)
        }
    }
}