package com.charlye934.newsjetpack.data.model


import com.google.gson.annotations.SerializedName

data class ApiResponse(
    @SerializedName("articles")
    val articles: List<Article>,
    @SerializedName("status")
    val status: String,
    @SerializedName("totalResults")
    val totalResults: Int
)