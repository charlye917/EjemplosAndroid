package com.charlye934.moviesjetpack.data.repository.movie

import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.model.movies.MovieList
import retrofit2.Response

interface MovieRepository {
    //Data Cache
    suspend fun getMovieFromCache(): List<Movie>
    suspend fun saveMoviesToCache(movies:List<Movie>)

    //Ddata DB
    suspend fun getMoviesFromDB(): List<Movie>
    suspend fun saveMoviesToDB(movies: List<Movie>)
    suspend fun clearAll()

    //Data Remote
    suspend fun getMoviesRemote(): Response<MovieList>
}