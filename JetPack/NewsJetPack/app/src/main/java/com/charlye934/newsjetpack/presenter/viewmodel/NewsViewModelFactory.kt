package com.charlye934.newsjetpack.presenter.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.charlye934.newsjetpack.domain.NewsInteractor

class NewsViewModelFactory (
    private val app: Application,
    private val getInteractor: NewsInteractor
    ): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return NewsViewModel(
            app,
            getInteractor
        ) as T
    }

}