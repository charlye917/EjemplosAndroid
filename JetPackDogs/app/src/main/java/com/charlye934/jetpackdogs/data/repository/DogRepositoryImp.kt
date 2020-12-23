package com.charlye934.jetpackdogs.data.repository

import android.content.Context
import com.charlye934.jetpackdogs.data.db.DogDao
import com.charlye934.jetpackdogs.data.db.DogDatabase
import com.charlye934.jetpackdogs.data.model.DogBreed
import com.charlye934.jetpackdogs.data.service.DogApiService

class DogRepositoryImp(context: Context) : DogsRepository{
    private val dogApiService = DogApiService
    private val dogDatabase = DogDatabase(context).dogDao()

    override suspend fun getDogsService(): List<DogBreed> {
        return dogApiService.getDogs()
    }

    override suspend fun insertAll(dogs: DogBreed): List<Long> {
        return dogDatabase.insertAll(dogs)
    }

    override suspend fun getAllDogs(): List<DogBreed> {
        return dogDatabase.getAllDogs()
    }

    override suspend fun getDogDB(dogId: Int): DogBreed {
        return dogDatabase.getDog(dogId)
    }

    override suspend fun deleteAllDogs() {
        dogDatabase.deleteAllDogs()
    }

}