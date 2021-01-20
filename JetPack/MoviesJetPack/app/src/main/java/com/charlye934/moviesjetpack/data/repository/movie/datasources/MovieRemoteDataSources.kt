package com.charlye934.moviesjetpack.data.repository.movie.datasources

import com.charlye934.moviesjetpack.data.model.movies.MovieList
import retrofit2.Response

interface MovieRemoteDataSources {
    suspend fun getMovies(): Response<MovieList>
}