package com.charlye934.moviesjetpack.data.repository.movie

import com.charlye934.moviesjetpack.data.api.TMDBService
import com.charlye934.moviesjetpack.data.db.MovieDao
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.model.movies.MovieList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class MovieRepositoryImp(
    private val movieDao: MovieDao,
    private val tmdbService: TMDBService,
    private val apiKey: String
): MovieRepository {

    private var movieList = ArrayList<Movie>()

    override suspend fun getMovieFromCache(): List<Movie> {
        return movieList
    }

    override suspend fun saveMoviesToCache(movies: List<Movie>) {
        movieList.clear()
        movieList = ArrayList(movies)
    }

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

    override suspend fun getMoviesRemote(): Response<MovieList> {
        return tmdbService.getPopularMovies(apiKey)

    }
}