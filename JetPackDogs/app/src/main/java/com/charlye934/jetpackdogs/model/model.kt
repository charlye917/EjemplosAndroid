package com.charlye934.jetpackdogs.model

import com.google.gson.annotations.SerializedName

data class  DogBreed(
    @SerializedName("id")
    val breedId: Int?,
    @SerializedName("name")
    val dogBreed: String?,
    @SerializedName("life_span")
    val lifeSpan: String?,
    @SerializedName("breed_group")
    val breedGroup: String?,
    @SerializedName("bred_for")
    val bredFor: String?,
    @SerializedName("temperament")
    val temperament: String?,
    @SerializedName("url")
    val imageUrl:String?
)