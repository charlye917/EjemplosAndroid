package com.charlye934.moviesjetpack.presentation.di.artist

import com.charlye934.moviesjetpack.domain.artist.ArtistInteractor
import com.charlye934.moviesjetpack.presentation.viewmodel.artist.ArtistViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ArtistModule {
    @ArtistScope
    @Provides
    fun provideArtistViewModelFactory(
        artistInteractor: ArtistInteractor
    ): ArtistViewModelFactory {
        return ArtistViewModelFactory(
            artistInteractor
        )
    }
}