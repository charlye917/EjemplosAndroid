package com.charlye934.moviesjetpack.domain.artist

import android.util.Log
import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.data.repository.artist.ArtistRepository
import java.lang.Exception

class ArtistInteractorImp(private val artistRepository: ArtistRepository) : ArtistInteractor {

    override suspend fun getArtist(): List<Artist>? {
        return getArtistFromCache()
    }

    override suspend fun updateArtsit(): List<Artist>? {
        val newListOfArtist = getArtistFromAPI()
        artistRepository.clearAll()
        artistRepository.saveArtistToDB(newListOfArtist!!)
        artistRepository.saveArtistToCache(newListOfArtist)
        return newListOfArtist
    }

    suspend fun getArtistFromAPI(): List<Artist>?{
        lateinit var artistList: List<Artist>
        try {
            val response = artistRepository.getArtistsRemote()
            val body = response.body()
            if(body != null){
                artistList = body.artist
            }
        }catch (e: Exception){
            Log.i("__TAG", e.message.toString())
        }
        return artistList
    }

    suspend fun getArtistFromDB(): List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = artistRepository.getArtistFromDB()
        }catch (e: Exception){
            Log.d("__TAG", e.message.toString())
        }

        if(artistList.size > 0){
            return artistList
        }else{
            artistList = getArtistFromAPI()!!
            artistRepository.saveArtistToDB(artistList)
        }

        return artistList
    }

    suspend fun getArtistFromCache(): List<Artist>{
        lateinit var artistList: List<Artist>
        try {
            artistList = artistRepository.getArtistFromCache()
        }catch (e: Exception){
            Log.d("__tag", e.message.toString())
        }

        if(artistList.size >= 0){
            return artistList
        }else{
            artistList = getArtistFromDB()
            artistRepository.saveArtistToCache(artistList)
        }
        return artistList
    }

}