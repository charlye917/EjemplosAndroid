package com.charlye934.moviesjetpack.data.repository.movie.datasources

import com.charlye934.moviesjetpack.data.model.movies.Movie

interface MovieLocalDataSources {
    suspend fun getMoviesFromDB(): List<Movie>
    suspend fun saveMoviesToDB(movies: List<Movie>)
    suspend fun clearAll()
}