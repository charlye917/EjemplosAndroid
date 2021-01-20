package com.charlye934.moviesjetpack.domain.usercase

import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.domain.repository.MovieRepository

class UpdateMovieUsecase(private val movieRepository: MovieRepository) {
    suspend fun execute(): List<Movie>? = movieRepository.updateMovies()
}