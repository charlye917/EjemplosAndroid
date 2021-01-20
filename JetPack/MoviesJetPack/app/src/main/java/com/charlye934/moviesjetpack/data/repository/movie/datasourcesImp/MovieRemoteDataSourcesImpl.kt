package com.charlye934.moviesjetpack.data.repository.movie.datasourcesImp

import com.charlye934.moviesjetpack.data.api.TMDBService
import com.charlye934.moviesjetpack.data.model.movies.MovieList
import com.charlye934.moviesjetpack.data.repository.movie.datasources.MovieRemoteDataSources
import retrofit2.Response

class MovieRemoteDataSourcesImpl(private val tmdbService: TMDBService, private val apiKey: String) :
    MovieRemoteDataSources {
    override suspend fun getMovies(): Response<MovieList> {
        return tmdbService.getPopularMovies(apiKey)
    }
}