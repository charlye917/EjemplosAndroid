package com.charlye934.jetpackdogs.presenter.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.charlye934.jetpackdogs.data.model.DogBreed
import com.charlye934.jetpackdogs.data.db.DogDatabase
import com.charlye934.jetpackdogs.data.service.DogsApiService
import com.charlye934.jetpackdogs.utils.NotificationHelper
import com.charlye934.jetpackdogs.utils.SharedPreferencesHelper

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class ListViewModel(application: Application) : BaseViewModel(application){

    private var prefHelper = SharedPreferencesHelper(getApplication())
    private var refreshTime =  5 * 60 *1000 * 1000 * 1000L
    private val dogsService = DogsApiService()
    private val disposible = CompositeDisposable()


    val dogs = MutableLiveData<List<DogBreed>>()
    val dogsLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh(){
        checkCacheDuration()
        val updateTime = prefHelper.getUpdateTime()
        if(updateTime != null && updateTime != 0L && System.nanoTime() - updateTime < refreshTime){
            fetchFromDatabase()
        }else{
            fetchFromRemote()
        }
        fetchFromRemote()
    }

    private fun checkCacheDuration(){
        val cachePreferences = prefHelper.getCacheDuration()

        try{
            val cachePreferencesInt = cachePreferences?.toInt() ?: 5 * 60
            refreshTime = cachePreferencesInt.times(60 * 1000 * 1000 * 1000L)
        } catch (e :NumberFormatException){
            e.printStackTrace()
        }
    }

    private fun fetchFromDatabase(){
        loading.value = true
        launch {
            val dogs = DogDatabase(getApplication()).dogDao().getAllDogs()
            dogRetrieved(dogs)
            Toast.makeText(getApplication(), "Dogs retrive from database", Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshBypassCache(){
        fetchFromRemote()
    }

    private fun fetchFromRemote(){
        loading.value = true
        disposible.addAll(
            dogsService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidScheduler)
                .subscribeWith(object : DisposableSingleObserver<List<DogBreed>>(){
                    override fun onSuccess(dogList: List<DogBreed>) {
                        storeDogsLocally(dogList)
                        Toast.makeText(getApplication(),"Dogs retrieved from endpoint", Toast.LENGTH_SHORT).show()
                        NotificationHelper(getApplication()).createNotification()
                    }

                    override fun onError(e: Throwable) {
                        dogsLoadError.value = true
                        loading.value = false
                        e.printStackTrace()
                    }
                })
        )
    }

    private fun dogRetrieved(dogList: List<DogBreed>){
        dogs.value = dogList
        dogsLoadError.value = false
        loading.value = false
    }

    private fun storeDogsLocally(list: List<DogBreed>){
        launch {
            val dao = DogDatabase(getApplication()).dogDao()
            dao.deleteAllDogs()
            val result = dao.insertAll(* list.toTypedArray())
            var i = 0
            while (i < list.size){
                list[i].uuid = result[i].toInt()
                i++
            }
            dogRetrieved(list)
        }
        prefHelper.saveUpdateTime(System.nanoTime())
    }


    override fun onCleared() {
        super.onCleared()
        disposible.clear()
    }
}