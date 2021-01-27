package com.charlye934.moviesjetpack.presentation.di.core

import com.charlye934.moviesjetpack.presentation.di.artist.ArtistSubComponent
import com.charlye934.moviesjetpack.presentation.di.movie.MovieSubComponent
import com.charlye934.moviesjetpack.presentation.di.tvshow.TvShowSubComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AppModule::class,
    DataBaseModule::class,
    InteractorModule::class,
    NetModule::class,
    RepositoryModule::class
])
interface AppComponent {
    fun movieSubComponent(): MovieSubComponent.Factory
    fun tvShowSubComponent(): TvShowSubComponent.Factory
    fun arttistSubComponent(): ArtistSubComponent.Factory
}