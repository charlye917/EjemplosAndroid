package com.charlye934.moviesjetpack.data.repository.movie.datasources

import com.charlye934.moviesjetpack.data.model.movies.Movie

interface MovieCacheDataSources {
    suspend fun getMovieFromCache(): List<Movie>
    suspend fun saveMoviesToCache(movies:List<Movie>)
}