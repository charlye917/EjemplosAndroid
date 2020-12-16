package com.charlye934.jetpackdogs.data.service

import com.charlye934.jetpackdogs.data.model.DogBreed
import io.reactivex.Single
import retrofit2.http.GET

interface DogsApi {
    @GET("/DevTides/DogsApi/master/dogs.json")
    fun getDogs(): Single<List<DogBreed>>
}