package com.charlye934.moviesjetpack.data.repository.tvshow

import com.charlye934.moviesjetpack.data.model.tvshow.TvShow

interface TvShowRepository {
    suspend fun getTvShows():List<TvShow>?
    suspend fun updateTvShows():List<TvShow>?
}