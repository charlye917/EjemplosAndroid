package com.charlye934.moviesjetpack.domain.tvshows

import com.charlye934.moviesjetpack.data.model.tvshow.TvShow

interface TvShowInteractor {
    suspend fun getTvShow(): List<TvShow>?
    suspend fun updateTvShow(): List<TvShow>?
}