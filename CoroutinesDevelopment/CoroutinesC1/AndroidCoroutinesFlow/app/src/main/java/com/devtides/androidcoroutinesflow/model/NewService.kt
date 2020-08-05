package com.devtides.androidcoroutinesflow.model

import com.devtides.androidcoroutinesretrofit.model.NewsArticle
import retrofit2.http.GET

interface NewService{
    @GET("news.json")
    suspend fun getNews(): List<NewsArticle>
}