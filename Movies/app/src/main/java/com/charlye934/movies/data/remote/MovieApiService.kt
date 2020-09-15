package com.charlye934.movies.data.remote

import com.charlye934.movies.data.remote.model.MoviesResponse
import io.reactivex.Observer
import retrofit2.http.GET

interface MovieApiService {

    @GET("movie/popular")
    fun loadPopularMovie(): Observer<MoviesResponse>
}