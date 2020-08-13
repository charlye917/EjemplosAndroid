package com.charlye934.loginddagger.http.pojo
import com.google.gson.annotations.SerializedName

data class Twitch(
        @SerializedName("top")
    val top: List<Top>,
        @SerializedName("_total")
    val total: Int
)