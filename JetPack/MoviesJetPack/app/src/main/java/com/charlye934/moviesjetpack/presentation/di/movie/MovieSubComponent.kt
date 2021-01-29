package com.charlye934.moviesjetpack.presentation.di.movie

import com.charlye934.moviesjetpack.presentation.fragment.movie.MovieFragment
import dagger.Subcomponent

@MovieScope
@Subcomponent(modules = [MovieModule::class])
interface MovieSubComponent {
    fun inject(movieFragment: MovieFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): MovieSubComponent
    }
}