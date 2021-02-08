package com.charlye934.moviesjetpack.data.db

import androidx.room.*
import com.charlye934.moviesjetpack.data.model.movies.Movie

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovies(movies: List<Movie>)

    @Query("DELETE FROM popular_movie")
    suspend fun deleteAllMovies()

    @Query("SELECT * FROM popular_movie")
    suspend fun getMovies(): List<Movie>
}