package com.charlye934.moviesjetpack.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.charlye934.moviesjetpack.data.model.artist.Artist

@Dao
interface ArtistDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveArtist(artist: List<Artist>)

    @Query("DELETE FROM popular_tv_show")
    suspend fun deleteAllArtist()

    @Query("SELECT * FROM popular_artists")
    suspend fun getPopularArtist(): List<Artist>
}