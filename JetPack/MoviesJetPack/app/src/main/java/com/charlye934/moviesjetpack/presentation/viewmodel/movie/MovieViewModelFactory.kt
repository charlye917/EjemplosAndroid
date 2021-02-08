package com.charlye934.moviesjetpack.presentation.viewmodel.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.charlye934.moviesjetpack.domain.movies.MovieInteractor

class MovieViewModelFactory( private val movieInteractor: MovieInteractor) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieViewModel(movieInteractor) as T
    }
}