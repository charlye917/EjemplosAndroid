package com.charlye934.moviesfeed.root

import com.charlye934.moviesfeed.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(app: MainActivity)
}