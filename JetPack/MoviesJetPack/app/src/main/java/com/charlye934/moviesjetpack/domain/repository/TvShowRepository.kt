package com.charlye934.moviesjetpack.domain.repository

import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.model.tvshow.TvShow

interface TvShowRepository {
    suspend fun getTvShow(): List<TvShow>?
    suspend fun updateTvShow(): List<TvShow>?
}