package com.charlye934.moviesjetpack.presentation.di.artist

import com.charlye934.moviesjetpack.presentation.fragment.ArtistFragment
import dagger.Subcomponent

@ArtistScope
@Subcomponent(modules = [ArtistModule::class])
interface ArtistSubComponent {
    fun inject(artistFragment: ArtistFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): ArtistSubComponent
    }
}