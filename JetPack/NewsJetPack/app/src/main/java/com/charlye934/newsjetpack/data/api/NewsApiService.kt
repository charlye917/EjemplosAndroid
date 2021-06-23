package com.charlye934.newsjetpack.data.api

import com.charlye934.newsjetpa.BuildConfig
import com.charlye934.newsjetpa.BuildConfig.API_KEY
import com.charlye934.newsjetpack.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country")
        country: String,
        @Query("page")
        page: Int,
        @Query("apiKey")
        apiKeyString: String = BuildConfig.API_KEY
    ): Response<ApiResponse>
}