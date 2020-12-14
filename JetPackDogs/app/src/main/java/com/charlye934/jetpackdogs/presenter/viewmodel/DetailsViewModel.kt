package com.charlye934.jetpackdogs.presenter.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.charlye934.jetpackdogs.data.model.DogBreed
import com.charlye934.jetpackdogs.data.db.DogDatabase
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