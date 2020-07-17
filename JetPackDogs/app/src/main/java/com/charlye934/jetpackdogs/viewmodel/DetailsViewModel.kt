package com.charlye934.jetpackdogs.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.charlye934.jetpackdogs.model.DogBreed

class DetailsViewModel : ViewModel(){

    val dogLiveData = MutableLiveData<DogBreed>()

    fun refresh(){
        val dog = DogBreed(1, "Corgi", "15 years", "BredGroup","BredFor","Temperament","")
        dogLiveData.value = dog

    }
}