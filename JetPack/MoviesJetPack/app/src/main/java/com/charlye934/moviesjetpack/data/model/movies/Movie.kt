package com.charlye934.moviesjetpack.data.model.movies


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "popular_movie")
data class Movie(
    @PrimaryKey
    @SerializedName("id")
    var id: Int,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String
)