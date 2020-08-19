package com.charlye934.popcorn.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.charlye934.popcorn.repository.TheMovieDBRepository
import com.charlye934.popcorn.retrofit.model.Movie

class MovieViewModel : ViewModel(){
    private var theMovieDBRepository: TheMovieDBRepository = TheMovieDBRepository()
    private var popularMovies: LiveData<List<Movie>>

    init {
        popularMovies = theMovieDBRepository.popularMovies()!!
    }

    fun getPopularMovies(): LiveData<List<Movie>>{
        return popularMovies
    }
}