package com.charlye934.loginddagger.http.twitch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Pagination(
        @SerializedName("cursor")
        @Expose
        private var cursor:String
)