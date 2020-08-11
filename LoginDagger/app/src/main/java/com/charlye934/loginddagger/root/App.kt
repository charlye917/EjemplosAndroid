package com.charlye934.loginddagger.root

import android.app.Application
import com.charlye934.loginddagger.login.LoginModule

class AppDagger() : Application(){
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .loginModule(LoginModule())
                .build()
    }

    fun getComponet(): ApplicationComponent = component
}