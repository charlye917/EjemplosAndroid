package com.charlye934.moviesjetpack.domain.interactor

import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.model.tvshow.TvShow

interface MoviesInteractor{
    suspend fun getArtist(): List<Artist>?
    suspend fun updateArtsit(): List<Artist>?

    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?

    suspend fun getTvShow(): List<TvShow>?
    suspend fun updateTvShow(): List<TvShow>?
}