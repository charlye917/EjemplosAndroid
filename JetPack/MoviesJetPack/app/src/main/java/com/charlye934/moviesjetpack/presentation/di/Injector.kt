package com.charlye934.moviesjetpack.presentation.di

import com.charlye934.moviesjetpack.presentation.di.artist.ArtistSubComponent
import com.charlye934.moviesjetpack.presentation.di.movie.MovieSubComponent
import com.charlye934.moviesjetpack.presentation.di.tvshow.TvShowSubComponent

interface Injector {
    fun createMovieSubComponent(): MovieSubComponent
    fun createTvShowSubcomponent(): TvShowSubComponent
    fun createArtistSubComponent(): ArtistSubComponent
}