package com.charlye934.moviesjetpack.data.repository.artist

import com.charlye934.moviesjetpack.data.api.TMDBService
import com.charlye934.moviesjetpack.data.db.ArtistDao
import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.data.model.artist.ArtistList
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.model.movies.MovieList
import com.charlye934.moviesjetpack.data.repository.movie.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class ArtistRepositoryImp(
    private val artistDao: ArtistDao,
    private val tmdbService: TMDBService,
    private val apiKey: String
) : ArtistRepository{

    private var artistList = ArrayList<Artist>()

    override suspend fun getArtistFromCache(): List<Artist> {
        return artistList
    }

    override suspend fun saveArtistToCache(artist: List<Artist>) {
        artistList.clear()
        artistList = ArrayList(artist)
    }

    override suspend fun getArtistFromDB(): List<Artist> {
        return artistDao.getPopularArtist()
    }

    override suspend fun saveArtistToDB(artist: List<Artist>) {
       CoroutineScope(Dispatchers.IO).launch {
           artistDao.saveArtist(artist)
       }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            artistDao.deleteAllArtist()
        }
    }

    override suspend fun getArtistsRemote(): Response<ArtistList> {
        return tmdbService.getPopularArtist(apiKey)
    }


}