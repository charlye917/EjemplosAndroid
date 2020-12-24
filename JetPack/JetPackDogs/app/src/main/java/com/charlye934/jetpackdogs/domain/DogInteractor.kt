package com.charlye934.jetpackdogs.domain

import com.charlye934.jetpackdogs.data.model.DogBreed

interface DogInteractor {
    suspend fun getDogsService(): List<DogBreed>
    suspend fun getDogDB(dogId: Int): DogBreed
}