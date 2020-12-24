package com.charlye934.jetpackdogs.presenter.viewmodel

import android.app.Application
import androidx.lifecycle.liveData
import com.charlye934.jetpackdogs.domain.DogInteractor
import com.charlye934.jetpackdogs.domain.DogInteractorImp

class DetailViewModel(application: Application) : BaseViewModel(application) {

    private val dogInteractor: DogInteractor = DogInteractorImp(application)

    fun fetch(uuid: Int) = liveData{
        val dog = dogInteractor.getDogDB(uuid)
        emit(dog)
    }
}