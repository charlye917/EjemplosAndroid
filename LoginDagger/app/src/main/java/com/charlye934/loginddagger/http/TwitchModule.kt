package com.charlye934.loginddagger.http

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class TwitchModule {
    private val BASE_URL = "https://api.twitch.tv/kraken/"

    @Provides
    fun provideHttpClient(): OkHttpClient{
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    @Provides
    fun provideRetrofit(baserUrl:String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baserUrl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun provideTwitchService(): TwitchAPI {
        return provideRetrofit(BASE_URL, provideHttpClient()).create(TwitchAPI::class.java)
    }
}