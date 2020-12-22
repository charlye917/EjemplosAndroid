package com.charlye934.jetpackdogs.data.db

import androidx.room.Insert
import androidx.room.Query
import com.charlye934.jetpackdogs.data.model.DogBreed

interface DogDao{
    @Insert
    suspend fun insertAll(vararg dogs: DogBreed): List<Long>

    @Query("SELECT * FROM dogbreed")
    suspend fun getAllDogs(): List<DogBreed>

    @Query("SELECT * FROM dogbreed WHERE uuid = :dogId")
    suspend fun getDog(dogId: Int): DogBreed

    @Query("DELETE FROM dogbreed")
    suspend fun deleteAllDogs()
}