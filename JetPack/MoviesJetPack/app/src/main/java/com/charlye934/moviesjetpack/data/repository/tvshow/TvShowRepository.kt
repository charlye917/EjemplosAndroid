package com.charlye934.moviesjetpack.data.repository.tvshow

import com.charlye934.moviesjetpack.data.model.tvshow.TvShow
import com.charlye934.moviesjetpack.data.model.tvshow.TvShowList
import retrofit2.Response

interface TvShowRepository {
    //DATA CACHE
    suspend fun getTvShowFromCache(): List<TvShow>
    suspend fun saveTvShowToCache(tvShows: List<TvShow>)

    //DATA DB
    suspend fun getTvShowsFromDB(): List<TvShow>
    suspend fun saveTvShowToDb(tvShows: List<TvShow>)
    suspend fun clearAll()

    //DATA REMOTE
    suspend fun getTvShowRemote(): Response<TvShowList>
}