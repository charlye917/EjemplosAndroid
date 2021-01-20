package com.charlye934.moviesjetpack.data.repository.movie.datasourcesImp

import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.repository.movie.datasources.MovieCacheDataSources
import com.charlye934.moviesjetpack.domain.repository.MovieRepository

class MovieCacheDataSourcesImp  : MovieCacheDataSources {

    private var movieList = ArrayList<Movie>()

    override suspend fun getMovieFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }


}