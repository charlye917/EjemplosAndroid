package com.charlye934.moviesjetpack.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.charlye934.moviesjetpack.data.model.tvshow.TvShow

@Dao
interface TVShowDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTvShows(tvShow: List<TvShow>)

    @Query("DELETE FROM popular_tv_show")
    suspend fun deleteAllTVShows()

    @Query("SELECT * FROM popular_tv_show")
    suspend fun getTVShow(): List<TvShow>
}