package com.charlye934.moviesjetpack.presentation.di.core

import com.charlye934.moviesjetpack.data.repository.artist.ArtistRepository
import com.charlye934.moviesjetpack.data.repository.movie.MovieRepository
import com.charlye934.moviesjetpack.data.repository.tvshow.TvShowRepository
import com.charlye934.moviesjetpack.domain.artist.ArtistInteractor
import com.charlye934.moviesjetpack.domain.artist.ArtistInteractorImp
import com.charlye934.moviesjetpack.domain.movies.MovieInteractor
import com.charlye934.moviesjetpack.domain.movies.MovieInteractorImp
import com.charlye934.moviesjetpack.domain.tvshows.TvShowInteractor
import com.charlye934.moviesjetpack.domain.tvshows.TvShowInteractorImp
import dagger.Module
import dagger.Provides

@Module
class InteractorModule {

    @Provides
    fun provideMovieInteractor(movieRepository: MovieRepository): MovieInteractor{
        return MovieInteractorImp(movieRepository)
    }

    @Provides
    fun provideArtistInteractor(artistRepository: ArtistRepository): ArtistInteractor {
        return ArtistInteractorImp(artistRepository)
    }

    @Provides
    fun provideTvShowInteractor(tvShowRepository: TvShowRepository): TvShowInteractor{
        return TvShowInteractorImp(tvShowRepository)
    }
}