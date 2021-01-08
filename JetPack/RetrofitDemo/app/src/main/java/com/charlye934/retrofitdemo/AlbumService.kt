package com.charlye934.retrofitdemo

import com.charlye934.retrofitdemo.model.Album
import retrofit2.Response
import retrofit2.http.GET


interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Album>
}