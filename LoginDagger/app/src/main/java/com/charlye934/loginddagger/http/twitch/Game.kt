package com.charlye934.loginddagger.http.twitch

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Game(
        @SerializedName("id")
        @Expose
        var id:String,

        @SerializedName("name")
        @Expose
        var name:String,

        @SerializedName("box_art_url")
        @Expose
        var boxArtUrl:String
)