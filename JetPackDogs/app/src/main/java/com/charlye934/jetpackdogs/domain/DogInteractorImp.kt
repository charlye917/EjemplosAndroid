package com.charlye934.jetpackdogs.domain

import android.content.Context
import com.charlye934.jetpackdogs.data.model.DogBreed
import com.charlye934.jetpackdogs.data.repository.DogRepositoryImp
import com.charlye934.jetpackdogs.data.repository.DogsRepository

class DogInteractorImp(context: Context) : DogInteractor{
    private val dogsRepository: DogsRepository = DogRepositoryImp(context)

    override suspend fun getDogsService(): List<DogBreed> {
        return dogsRepository.getDogsService()
    }


}