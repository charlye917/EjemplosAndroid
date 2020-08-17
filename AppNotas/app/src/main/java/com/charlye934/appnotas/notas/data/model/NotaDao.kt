package com.charlye934.appnotas.notas.data.model

import androidx.lifecycle.LiveData
import androidx.room.*
import kotlinx.android.parcel.IgnoredOnParcel

@Dao
interface NotaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(nota:NotaEntity)

    @Update
    fun update(nota: NotaEntity)

    @Query("DELETE FROM notas")
    fun deleteAll()

    @Query("DELETE FROM notas WHERE id = :idNota")
    fun deleteById(idNota: Int)

    @Query("SELECT * FROM notas ORDER BY titulo ASC")
    fun getAll(): LiveData<List<NotaEntity>>

    @Query("SELECT * FROM notas WHERE favorito LIKE 'true'")
    fun getAllFavorte(): LiveData<List<NotaEntity>>

}