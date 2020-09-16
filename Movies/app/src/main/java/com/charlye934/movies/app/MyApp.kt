package com.charlye934.movies.app

import android.app.Application

object MyApp : Application() {

    private lateinit var instace: MyApp

    fun getInstance() = instace
    fun getContext() = instace


    override fun onCreate() {
        instace = this
        super.onCreate()
    }
}