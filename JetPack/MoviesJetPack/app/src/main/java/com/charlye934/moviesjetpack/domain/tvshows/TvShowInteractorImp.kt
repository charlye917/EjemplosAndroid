package com.charlye934.moviesjetpack.domain.tvshows

class TvShowInteractorImp(private val tvShowInteractor: TvShowInteractor) {
    suspend fun getTvShows() = tvShowInteractor.getTvShow()
    suspend fun updateTvShows() = tvShowInteractor.updateTvShow()
}