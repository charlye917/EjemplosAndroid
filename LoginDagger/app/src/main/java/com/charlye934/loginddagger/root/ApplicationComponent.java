package com.charlye934.loginddagger.root;

import com.charlye934.loginddagger.http.TwitchModule;
import com.charlye934.loginddagger.http.twitch.Twitch;
import com.charlye934.loginddagger.login.LoginActivity;
import com.charlye934.loginddagger.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class, TwitchModule.class})
public interface ApplicationComponent {
    void inject(LoginActivity target);
}
