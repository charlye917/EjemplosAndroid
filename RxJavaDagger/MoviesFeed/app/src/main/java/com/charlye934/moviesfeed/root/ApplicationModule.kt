package com.charlye934.moviesfeed.root

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val app: App){

    @Provides
    @Singleton
    fun provideContext(): Context = app

}