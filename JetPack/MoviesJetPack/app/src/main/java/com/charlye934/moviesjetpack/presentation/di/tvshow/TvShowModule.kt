package com.charlye934.moviesjetpack.presentation.di.tvshow

import com.charlye934.moviesjetpack.domain.tvshows.TvShowInteractor
import com.charlye934.moviesjetpack.presentation.di.artist.ArtistScope
import com.charlye934.moviesjetpack.presentation.viewmodel.tvshow.TvShowViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class TvShowModule {
    @TvShowScope
    @Provides
    fun provideTvShowViewModelFactory(
        tvShowInteractor: TvShowInteractor
    ): TvShowViewModelFactory{
        return TvShowViewModelFactory(
            tvShowInteractor
        )
    }
}