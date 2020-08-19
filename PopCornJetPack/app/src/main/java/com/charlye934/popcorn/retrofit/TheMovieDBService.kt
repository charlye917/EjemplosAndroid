package com.charlye934.popcorn.retrofit

import com.charlye934.popcorn.retrofit.model.PopularMoviesResponse
import retrofit2.Call
import retrofit2.http.GET

interface TheMovieDBService {
    @GET("movie/popular")
    fun getPopulaarMoves(): Call<PopularMoviesResponse>
}