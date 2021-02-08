package com.charlye934.moviesjetpack.presentation.viewmodel.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.moviesjetpack.domain.tvshows.TvShowInteractor

class TvShowViewModel(private val tvShowInteractor: TvShowInteractor): ViewModel() {

    fun getTvShow() = liveData {
        val result = tvShowInteractor.getTvShow()
        emit(result)
    }

    fun updateTvShow() = liveData {
        val result = tvShowInteractor.updateTvShow()
        emit(result)
    }
}