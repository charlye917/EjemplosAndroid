package com.charlye934.jetpackdogs.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charlye934.jetpackdogs.model.DogBreed
import com.charlye934.jetpackdogs.model.DogDatabase
import kotlinx.coroutines.launch

class DetailsViewModel(application: Application) : BaseViewModel(application){

    val dogLiveData = MutableLiveData<DogBreed>()

    fun fetch(uuid: Int){
        launch {
            val dog = DogDatabase(getApplication()).dogDao().getDog(uuid)
            dogLiveData.value = dog
        }


    }
}