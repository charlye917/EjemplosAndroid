package com.charlye934.moviesjetpack.domain.tvshows

import android.util.Log
import com.charlye934.moviesjetpack.data.model.tvshow.TvShow
import com.charlye934.moviesjetpack.data.repository.tvshow.TvShowRepository
import java.lang.Exception

class TvShowInteractorImp(private val tvShowRepository: TvShowRepository): TvShowInteractor {

    override suspend fun getTvShow(): List<TvShow>? {
        return getTvShowsFromCache()
    }

    override suspend fun updateTvShow(): List<TvShow>? {
        val newListTvShow = getTvShowsFromAPI()
        tvShowRepository.clearAll()
        tvShowRepository.saveTvShowToDb(newListTvShow)
        tvShowRepository.saveTvShowToCache(newListTvShow)
        return newListTvShow
    }

    suspend fun getTvShowsFromAPI(): List<TvShow>{
        lateinit var tvShowList: List<TvShow>
        try {
            val response = tvShowRepository.getTvShowRemote()
            val body = response.body()
            if(body != null)
                tvShowList = body.tvShows
        }catch (e: Exception){
            Log.d("__TAG", e.message.toString())
        }

        return tvShowList
    }

    suspend fun getTvShowsFromDB(): List<TvShow>{
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList = tvShowRepository.getTvShowsFromDB()
        }catch (e: Exception){
            Log.d("__TAG", e.message.toString())
        }
        if(tvShowList.size > 0)
            return tvShowList
        else{
            tvShowList = getTvShowsFromAPI()
            tvShowRepository.saveTvShowToDb(tvShowList)
        }
        return tvShowList
    }

    suspend fun getTvShowsFromCache(): List<TvShow>{
        lateinit var tvShowList: List<TvShow>
        try {
            tvShowList = tvShowRepository.getTvShowFromCache()
        }catch (e: Exception){
            Log.d("__TAG", e.message.toString())
        }
        if(tvShowList.size >= 0){
            return tvShowList
        }else{
            tvShowList = getTvShowsFromDB()
            tvShowRepository.saveTvShowToCache(tvShowList)
        }
        return tvShowList
    }

}