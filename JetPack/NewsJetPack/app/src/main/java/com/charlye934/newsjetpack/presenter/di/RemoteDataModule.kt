package com.charlye934.newsjetpack.presenter.di

import com.charlye934.newsjetpack.data.api.NewsApiService
import com.charlye934.newsjetpack.data.repository.NewsRepository
import com.charlye934.newsjetpack.data.repository.NewsRepositoryImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
class RemoteDataModule {

    @Singleton
    @Provides
    fun provideNewRemoteDataSource(
        newsApiService: NewsApiService
    ): NewsRepository{
        return NewsRepositoryImp(newsApiService)
    }
}