package com.charlye934.moviesfeed.root

import android.app.Application

class App : Application(){
    private lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this))
            .build()
    }

    fun getComponet(): ApplicationComponent = component
}