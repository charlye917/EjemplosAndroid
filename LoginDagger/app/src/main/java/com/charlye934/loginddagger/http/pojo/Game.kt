package com.charlye934.loginddagger.http.pojo
import com.google.gson.annotations.SerializedName

data class Game(
        @SerializedName("box")
    val box: Box,
        @SerializedName("giantbomb_id")
    val giantbombId: Int,
        @SerializedName("_id")
    val id: Int,
        @SerializedName("locale")
    val locale: String,
        @SerializedName("localized_name")
    val localizedName: String,
        @SerializedName("logo")
    val logo: Logo,
        @SerializedName("name")
    val name: String
)