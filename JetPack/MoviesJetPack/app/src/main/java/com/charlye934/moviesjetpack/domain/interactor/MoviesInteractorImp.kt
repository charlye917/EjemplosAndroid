package com.charlye934.moviesjetpack.domain.interactor

import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.model.tvshow.TvShow

class MoviesInteractorImp (private val moviesInteractor: MoviesInteractor){
    suspend fun getArtist() = moviesInteractor.getArtist()
    suspend fun updateArtist() = moviesInteractor.updateArtsit()
    suspend fun getMovies() = moviesInteractor.getArtist()
    suspend fun updateMovies() = moviesInteractor.updateMovies()
    suspend fun getTvShows() = moviesInteractor.getTvShow()
    suspend fun updateTvShows() = moviesInteractor.updateTvShow()
}