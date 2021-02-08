package com.charlye934.newsjetpack.presenter.viewmodel

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.charlye934.newsjetpack.data.model.ApiResponse
import com.charlye934.newsjetpack.domain.NewsInteractor
import com.charlye934.newsjetpack.util.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception

class NewsViewModel(
        private val app: Application,
        private val getNewsHeadlinesInteractor: NewsInteractor
): AndroidViewModel(app) {

    val newsHeadLines: MutableLiveData<Resource<ApiResponse>> = MutableLiveData()

    fun getNewHeadLines(country: String, page: Int) = viewModelScope.launch(Dispatchers.IO){
        newsHeadLines.postValue(Resource.Loading())
        try {
            if(isNetworkAvaible(app)){
                val apiResult = getNewsHeadlinesInteractor.getNewsHeadlines(country, page)
                newsHeadLines.postValue(apiResult)
            }else{
                newsHeadLines.postValue(Resource.Error("Internet is not avaible"))
            }
        }catch (e: Exception){
            newsHeadLines.postValue(Resource.Error(e.message.toString()))
        }
    }

    private fun isNetworkAvaible(context: Context?): Boolean{
        if(context == null) return false

        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
            val capabilities = connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
            if(capabilities != null){
                when{
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ->{
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ->{
                        return true
                    }
                    capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) ->{
                        return true
                    }
                }
            }else{
                val activityNetworkInfo = connectivityManager.activeNetworkInfo
                if(activityNetworkInfo != null && activityNetworkInfo.isConnected)
                    return true
            }
        }
        return false
    }
}