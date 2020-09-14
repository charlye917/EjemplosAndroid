package com.charlye934.movies

import android.app.Application

class MyApp : Application() {
    private lateinit var instace: MyApp

    fun getInstance() = instace
    fun getContext() = instace

    override fun onCreate() {
        instace = this
        super.onCreate()
    }
}