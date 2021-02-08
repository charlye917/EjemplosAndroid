package com.charlye934.moviesjetpack.data.model.artist


import com.google.gson.annotations.SerializedName

data class ArtistList(
    @SerializedName("results")
    val artist: List<Artist>
)