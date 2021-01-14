package com.charlye934.jetpackdogs.repository

import android.content.Context
import com.charlye934.jetpackdogs.model.db.DogBreed
import com.charlye934.jetpackdogs.model.db.DogDao
import com.charlye934.jetpackdogs.model.db.DogDataBase
import com.charlye934.jetpackdogs.model.service.DogsApi

class DogsRepositoryImp(context: Context) {
    private val dogDao = DogDataBase(context).dogDao()
    private val dogService = DogsApi.Builder().build()

    suspend fun getDogs() = dogService.getDogs()

    suspend fun insertDogs(dogs: DogBreed) = dogDao.insertAll(dogs)

    suspend fun getAllDogs() = dogDao.getAllDogs()

    suspend fun getDog(dogId: Int) = dogDao.getDog(dogId)

    suspend fun deleteAllDogs() = dogDao.deleteAllDogs()
}