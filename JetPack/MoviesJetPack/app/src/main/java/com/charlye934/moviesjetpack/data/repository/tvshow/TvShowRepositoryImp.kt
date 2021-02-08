package com.charlye934.moviesjetpack.data.repository.tvshow

import com.charlye934.moviesjetpack.data.api.TMDBService
import com.charlye934.moviesjetpack.data.db.TVShowDao
import com.charlye934.moviesjetpack.data.model.tvshow.TvShow
import com.charlye934.moviesjetpack.data.model.tvshow.TvShowList
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response

class TvShowRepositoryImp(
    private val tvShowDao: TVShowDao,
    private val tmdbService: TMDBService,
    private val apiKey: String
): TvShowRepository {

    private var tvShowList = ArrayList<TvShow>()

    override suspend fun getTvShowFromCache(): List<TvShow> {
        return tvShowList
    }

    override suspend fun saveTvShowToCache(tvShows: List<TvShow>) {
        tvShowList.clear()
        tvShowList = ArrayList(tvShows)
    }

    override suspend fun getTvShowsFromDB(): List<TvShow> {
        return tvShowDao.getTVShow()
    }

    override suspend fun saveTvShowToDb(tvShows: List<TvShow>) {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.saveTvShows(tvShowList)
        }
    }

    override suspend fun clearAll() {
        CoroutineScope(Dispatchers.IO).launch {
            tvShowDao.deleteAllTVShows()
        }
    }

    override suspend fun getTvShowRemote(): Response<TvShowList> {
        return tmdbService.getPopularTvShow(apiKey)
    }
}