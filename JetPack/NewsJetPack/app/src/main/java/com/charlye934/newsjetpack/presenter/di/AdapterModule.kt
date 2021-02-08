package com.charlye934.newsjetpack.presenter.di

import com.charlye934.newsjetpack.presenter.fragment.NewsAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AdapterModule {
    @Singleton
    @Provides
    fun provideNewsAdapter(): NewsAdapter{
        return NewsAdapter()
    }
}