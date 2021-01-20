package com.charlye934.moviesjetpack.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.data.model.tvshow.TvShow

@Database(entities = [Movie::class, TvShow::class, Artist::class], version = 1, exportSchema = false)
abstract class TMDBDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao
    abstract fun tvDao(): TvShow
    abstract fun artistDao(): Artist

}