package com.charlye934.moviesjetpack.data.model.movies


import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val results: List<Movie>
)