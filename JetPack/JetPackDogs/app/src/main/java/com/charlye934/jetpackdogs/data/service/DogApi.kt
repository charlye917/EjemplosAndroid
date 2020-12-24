package com.charlye934.jetpackdogs.data.service

import com.charlye934.jetpackdogs.data.model.DogBreed
import retrofit2.http.GET

interface DogApi{
    @GET("/DevTides/DogsApi/master/dogs.json")
    suspend fun getDogs(): List<DogBreed>
}