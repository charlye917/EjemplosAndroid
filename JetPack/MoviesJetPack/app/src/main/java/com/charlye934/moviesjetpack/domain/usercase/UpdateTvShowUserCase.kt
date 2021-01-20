package com.charlye934.moviesjetpack.domain.usercase

import com.charlye934.moviesjetpack.data.model.tvshow.TvShow
import com.charlye934.moviesjetpack.domain.repository.TvShowRepository

class UpdateTvShowUserCase(private val tvShowRepository: TvShowRepository) {
    suspend fun execute(): List<TvShow>? = tvShowRepository.updateTvShow()
}