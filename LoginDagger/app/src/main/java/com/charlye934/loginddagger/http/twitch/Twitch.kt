package com.charlye934.loginddagger.http.twitch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Twitch(
        @SerializedName("data")
        @Expose
        var game : List<Game>? = null,
        @SerializedName("pagination")
        @Expose
        var pagination: Pagination
)