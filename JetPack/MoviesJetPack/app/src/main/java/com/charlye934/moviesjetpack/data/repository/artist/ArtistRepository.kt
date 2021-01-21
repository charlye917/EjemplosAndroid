package com.charlye934.moviesjetpack.data.repository.artist

import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.data.model.artist.ArtistList
import retrofit2.Response

interface ArtistRepository {
    //CACHE
    suspend fun getArtistFromCache(): List<Artist>
    suspend fun saveArtistToCache(artist: List<Artist>)

    //DB
    suspend fun getArtistFromDB(): List<Artist>
    suspend fun saveArtistToDB(artist: List<Artist>)
    suspend fun clearAll()

    //Remote
    suspend fun getArtists(): Response<ArtistList>

}