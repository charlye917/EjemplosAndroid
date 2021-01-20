package com.charlye934.moviesjetpack.data.model.tvshow


import com.google.gson.annotations.SerializedName

data class TvShowList(
    @SerializedName("results")
    val results: List<TvShow>
)