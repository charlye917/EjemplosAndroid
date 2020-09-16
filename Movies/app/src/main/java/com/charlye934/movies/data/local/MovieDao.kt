package com.charlye934.movies.data.local

import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.charlye934.movies.data.local.MovieEntity
import io.reactivex.Single

interface MovieDao{
    @Query("SELECT * FROM movies")
    fun loadMovies(): Single<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMovies(movieEntity: List<MovieEntity>)

}