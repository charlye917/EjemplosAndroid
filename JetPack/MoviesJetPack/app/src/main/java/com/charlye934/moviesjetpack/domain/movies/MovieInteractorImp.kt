package com.charlye934.moviesjetpack.domain.movies

import android.util.Log
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.repository.movie.MovieRepository
import java.lang.Exception

class MovieInteractorImp(
    private val movieRepository: MovieRepository
): MovieInteractor {

    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies = getMoviesFromAPI()
        movieRepository.clearAll()
        movieRepository.saveMoviesToDB(newListOfMovies!!)
        movieRepository.saveMoviesToCache(newListOfMovies)
        return newListOfMovies
    }

    suspend fun getMoviesFromAPI(): List<Movie>?{
        lateinit var movielist: List<Movie>
        try {
            val response = movieRepository.getMoviesRemote()
            val body = response.body()
            if(body != null)
                movielist = body.movies
        }catch (exeption: Exception){
            Log.i("MyTag", exeption.message.toString())
        }

        return movielist
    }

    suspend fun getMoviesFromDB(): List<Movie>{
        lateinit var  movieList: List<Movie>
        try {
            movieList = movieRepository.getMoviesFromDB()
        }catch (exeption: Exception){
            Log.i("MyTag", exeption.message.toString())
        }

        if(movieList.size > 0){
            return movieList
        }else{
            movieList = getMoviesFromAPI()!!
            movieRepository.saveMoviesToDB(movieList)
        }

        return movieList
    }

    suspend fun getMoviesFromCache(): List<Movie>{
        lateinit var movieList: List<Movie>
        try{
            movieList = movieRepository.getMovieFromCache()
        }catch (exception: Exception){
            Log.i("MyTag",exception.message.toString())
        }
        if(movieList.size > 0 ){
            return movieList
        }else{
            movieList = getMoviesFromDB()
            movieRepository.saveMoviesToCache(movieList)
        }

        return movieList
    }
}