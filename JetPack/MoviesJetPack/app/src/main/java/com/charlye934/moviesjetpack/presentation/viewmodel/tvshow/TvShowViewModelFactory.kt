package com.charlye934.moviesjetpack.presentation.viewmodel.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.charlye934.moviesjetpack.domain.tvshows.TvShowInteractor

class TvShowViewModelFactory(private val tvShowInteractor: TvShowInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TvShowViewModel(tvShowInteractor) as T
    }

}