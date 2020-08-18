package com.charlye934.appnotas.notas.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import io.reactivex.Completable

@Dao
interface NotaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(nota: NotaEntity)

    @Update
    suspend fun update(nota: NotaEntity)

    @Query("DELETE FROM notas")
    suspend fun deleteAll()

    @Query("DELETE FROM notas WHERE id = :idNota")
    suspend fun deleteById(idNota: Int)

    @Query("SELECT * FROM notas ORDER BY titulo ASC")
    suspend fun getAll(): List<NotaEntity>

    @Query("SELECT * FROM notas WHERE favorito LIKE 'true'")
    suspend fun getAllFavorite(): List<NotaEntity>

}