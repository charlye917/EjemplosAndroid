package com.charlye934.moviesjetpack.presentation

import android.app.Application
import com.charlye934.moviesjetpack.BuildConfig
import com.charlye934.moviesjetpack.presentation.di.Injector
import com.charlye934.moviesjetpack.presentation.di.artist.ArtistSubComponent
import com.charlye934.moviesjetpack.presentation.di.core.*
import com.charlye934.moviesjetpack.presentation.di.movie.MovieSubComponent
import com.charlye934.moviesjetpack.presentation.di.tvshow.TvShowSubComponent
import javax.inject.Inject

class App : Application(), Injector {

    private lateinit var appComponet: AppComponent

    override fun onCreate(){
        super.onCreate()

       appComponet = DaggerAppComponent.builder()
            .appModule(AppModule(applicationContext))
            .netModule(NetModule(BuildConfig.BASE_URL))
           .repositoryModule(RepositoryModule(BuildConfig.API_KEY))
           .build()
    }

    override fun createMovieSubComponent(): MovieSubComponent {
        return appComponet.movieSubComponent().create()
    }

    override fun createTvShowSubcomponent(): TvShowSubComponent {
        return appComponet.tvShowSubComponent().create()
    }

    override fun createArtistSubComponent(): ArtistSubComponent {
        return appComponet.arttistSubComponent().create()
    }
}