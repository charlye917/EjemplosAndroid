package com.charlye934.newsjetpack.presenter.di

import com.charlye934.newsjetpack.data.repository.NewsRepository
import com.charlye934.newsjetpack.domain.NewsInteractor
import com.charlye934.newsjetpack.domain.NewsInteractorImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class InteractorModule {
    @Singleton
    @Provides
    fun provideGetNewInteractor(
        newsRepository: NewsRepository
    ): NewsInteractor{
        return NewsInteractorImp(newsRepository)
    }
}