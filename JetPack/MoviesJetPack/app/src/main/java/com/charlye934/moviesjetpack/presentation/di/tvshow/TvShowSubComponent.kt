package com.charlye934.moviesjetpack.presentation.di.tvshow

import com.charlye934.moviesjetpack.presentation.fragment.TvShowFragment
import dagger.Subcomponent

@TvShowScope
@Subcomponent
interface TvShowSubComponent {
    fun inject(tvShowFragment: TvShowFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): TvShowSubComponent
    }
}