package com.charlye934.jetpackdogs.presenter.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.charlye934.jetpackdogs.data.db.DogDatabase
import com.charlye934.jetpackdogs.data.model.DogBreed
import com.charlye934.jetpackdogs.domain.DogInteractor
import com.charlye934.jetpackdogs.domain.DogInteractorImp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListViewModel(application: Application) : BaseViewModel(application){
    private val dogInteractor: DogInteractor = DogInteractorImp(application)

    val dogs = MutableLiveData<List<DogBreed>>()

    fun refresh() = liveData{
        val response =  dogInteractor.getDogsService()
        emit(response)

    }
}