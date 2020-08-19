package com.charlye934.popcorn.retrofit.model

import com.charlye934.popcorn.retrofit.model.Movie

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)