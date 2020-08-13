package com.charlye934.loginddagger.root

import com.charlye934.loginddagger.http.TwitchModule
import com.charlye934.loginddagger.login.LoginModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class, LoginModule::class, TwitchModule::class])
interface ApplicationComponent {
    fun inject(target: com.charlye934.loginddagger.login.LoginActivity)
}