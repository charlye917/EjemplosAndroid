package com.bancoazteca.dogsjetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bancoazteca.dogsjetpack.model.DogBreed

class ListViewModel : ViewModel() {
    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        val dog1 = DogBreed("1", "Corgi", "15 years", "BredGroup","BredFor","Temperament","")
        val dog2 = DogBreed("2", "Labrador", "10 years", "BredGroup","BredFor","Temperament","")
        val dog3 = DogBreed("3", "Rotwailer", "20 years", "BredGroup","BredFor","Temperament","")
        val dogList = arrayListOf(dog1, dog2, dog3)

        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }
}