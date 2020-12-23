package com.charlye934.jetpackdogs.data.repository

import com.charlye934.jetpackdogs.data.model.DogBreed

interface DogsRepository {
    suspend fun getDogsService(): List<DogBreed>
    suspend fun insertAll(dogs: DogBreed): List<Long>
    suspend fun getAllDogs(): List<DogBreed>
    suspend fun getDogDB(dogId: Int): DogBreed
    suspend fun deleteAllDogs()
}