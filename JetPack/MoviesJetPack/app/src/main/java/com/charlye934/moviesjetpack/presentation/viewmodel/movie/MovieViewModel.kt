package com.charlye934.moviesjetpack.presentation.viewmodel.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.moviesjetpack.domain.movies.MovieInteractor

class MovieViewModel(private val movieInteractor: MovieInteractor) : ViewModel(){

    fun getMovies() = liveData {
        val result = movieInteractor.getMovies()
        emit(result)
    }

    fun updateMovies() = liveData{
        val result = movieInteractor.updateMovies()
        emit(result)
    }
}