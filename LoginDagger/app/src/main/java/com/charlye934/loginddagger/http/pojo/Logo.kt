package com.charlye934.loginddagger.http.pojo
import com.google.gson.annotations.SerializedName

data class Logo(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("small")
    val small: String,
    @SerializedName("template")
    val template: String
)