package com.charlye934.moviesjetpack.presentation.di.movie

import com.charlye934.moviesjetpack.domain.movies.MovieInteractor
import com.charlye934.moviesjetpack.presentation.viewmodel.movie.MovieViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MovieModule {
    @MovieScope
    @Provides
    fun provideMovieViewModelFactory(
        movieInteractor: MovieInteractor
    ): MovieViewModelFactory{
        return MovieViewModelFactory(
            movieInteractor
        )
    }
}