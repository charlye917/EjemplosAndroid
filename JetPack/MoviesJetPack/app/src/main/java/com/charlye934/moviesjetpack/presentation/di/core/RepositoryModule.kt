package com.charlye934.moviesjetpack.presentation.di.core

import com.charlye934.moviesjetpack.data.api.TMDBService
import com.charlye934.moviesjetpack.data.db.ArtistDao
import com.charlye934.moviesjetpack.data.db.MovieDao
import com.charlye934.moviesjetpack.data.db.TVShowDao
import com.charlye934.moviesjetpack.data.repository.artist.ArtistRepository
import com.charlye934.moviesjetpack.data.repository.artist.ArtistRepositoryImp
import com.charlye934.moviesjetpack.data.repository.movie.MovieRepository
import com.charlye934.moviesjetpack.data.repository.movie.MovieRepositoryImp
import com.charlye934.moviesjetpack.data.repository.tvshow.TvShowRepository
import com.charlye934.moviesjetpack.data.repository.tvshow.TvShowRepositoryImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule(
    private val apiKey: String
) {

    @Singleton
    @Provides
    fun provideMovieDataSources(
        movieDao: MovieDao,
        tmdbService: TMDBService,
    ): MovieRepository{
        return MovieRepositoryImp(
            movieDao,
            tmdbService,
            apiKey
        )
    }

    @Singleton
    @Provides
    fun provideTvDataSources(
        tvShowDao: TVShowDao,
        tmdbService: TMDBService,
    ): TvShowRepository{
        return TvShowRepositoryImp(
            tvShowDao,
            tmdbService,
            apiKey
        )
    }

    @Singleton
    @Provides
    fun provideArtistDataSources(
        artsitDao: ArtistDao,
        tmbdService: TMDBService,
    ): ArtistRepository{
        return ArtistRepositoryImp(
            artsitDao,
            tmbdService,
            apiKey
        )
    }
}