package com.charlye934.newsjetpack.presenter.di

import android.app.Application
import com.charlye934.newsjetpack.domain.NewsInteractor
import com.charlye934.newsjetpack.presenter.viewmodel.NewsViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class FactoryModule {
    @Singleton
    @Provides
    fun provideNewsViewModelFactory(
        application: Application,
        getNewsInteractor: NewsInteractor
    ): NewsViewModelFactory{
        return NewsViewModelFactory(
            application,
            getNewsInteractor
        )
    }
}