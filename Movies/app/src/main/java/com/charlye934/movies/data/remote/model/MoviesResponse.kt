package com.charlye934.movies.data.remote.model

import com.charlye934.movies.data.local.MovieEntity

data class MoviesResponse(
    val results: List<MovieEntity>
)