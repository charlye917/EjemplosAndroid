package com.charlye934.moviesjetpack.domain.movies

import com.charlye934.moviesjetpack.data.model.movies.Movie


interface MovieInteractor {
    suspend fun getMovies():List<Movie>?
    suspend fun updateMovies():List<Movie>?
}