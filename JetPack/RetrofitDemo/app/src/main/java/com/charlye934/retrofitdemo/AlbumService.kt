package com.charlye934.retrofitdemo

import com.charlye934.retrofitdemo.model.Album
import com.charlye934.retrofitdemo.model.AlbumItem
import retrofit2.Response
import retrofit2.http.*


interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Album>

    @GET("/albums")
    suspend fun getSortedAlbums(@Query("userId") userId: Int): Response<Album>

    @GET("/albums/{id}")
    suspend fun getAlbum(@Path(value = "id") id: String): Response<AlbumItem>

    @POST("/albums")
    suspend fun uploadAlbum(@Body album:AlbumItem): Response<AlbumItem>
}