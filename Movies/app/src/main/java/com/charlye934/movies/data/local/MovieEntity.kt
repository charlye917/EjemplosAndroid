package com.charlye934.movies.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieEntity(

    @PrimaryKey
    @SerializedName("popularity")
    @Expose
    val popularity:Double,

    @SerializedName("vote_count")
    @Expose
    val voteCount: Int,

    @SerializedName("video")
    @Expose
    val video:Boolean,

    @SerializedName("poster_path")
    @Expose
    val posterPaht: String,

    @SerializedName("id")
    @Expose
    val id:Int,

    @SerializedName("adult")
    @Expose
    val adult:Boolean,

    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String,

    @SerializedName("original_language")
    @Expose
    private val originalLanguage: String,

    @SerializedName("original_title")
    @Expose
    val originalTitle: String,

    @SerializedName("title")
    @Expose
    private val title: String,

    @SerializedName("vote_average")
    @Expose
    private val voteAverage: Double = 0.0,

    @SerializedName("overview")
    @Expose
   val overview: String,

    @SerializedName("release_date")
    @Expose
    val releasedData:String
)