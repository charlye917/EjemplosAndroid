package com.charlye934.roomdemo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.charlye934.roomdemo.db.SubscriberDAO
import java.lang.IllegalArgumentException

class SubscriberViewModelFactory(private val dao : SubscriberDAO):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
     if(modelClass.isAssignableFrom(SubscriberViewModel::class.java)){
         return SubscriberViewModel(dao) as T
     }
        throw IllegalArgumentException("Unknown View Model class")
    }
}