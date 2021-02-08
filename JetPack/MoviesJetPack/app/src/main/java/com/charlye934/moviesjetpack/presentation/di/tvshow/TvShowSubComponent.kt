package com.charlye934.moviesjetpack.presentation.di.tvshow

import com.charlye934.moviesjetpack.presentation.fragment.tvshow.TvShowFragment
import dagger.Subcomponent

@TvShowScope
@Subcomponent(modules = [TvShowModule::class])
interface TvShowSubComponent {
    fun inject(tvShowFragment: TvShowFragment)

    @Subcomponent.Factory
    interface Factory{
        fun create(): TvShowSubComponent
    }
}