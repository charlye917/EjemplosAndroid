package com.charlye934.moviesjetpack.data.repository.movie.datasourcesImp

import com.charlye934.moviesjetpack.data.db.MovieDao
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.repository.movie.datasources.MovieLocalDataSources
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieLocalDataSourcesImp(private val movieDao: MovieDao): MovieLocalDataSources {
    override suspend fun getMoviesFromDB(): List<Movie> {
        return movieDao.getMovies()
    }

    override suspend fun saveMoviesToDB(movies: List<Movie>) {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.saveMovies(movies)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            movieDao.deleteAllMovies()
        }
    }

}