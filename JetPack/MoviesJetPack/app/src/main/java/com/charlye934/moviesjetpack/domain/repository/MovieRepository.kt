package com.charlye934.moviesjetpack.domain.repository

import com.charlye934.moviesjetpack.data.model.movies.Movie

interface MovieRepository {
    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?
}