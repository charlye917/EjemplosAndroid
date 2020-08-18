package com.charlye934.appnotas.notas.data.repository

import androidx.lifecycle.LiveData
import com.charlye934.appnotas.notas.data.db.NotaEntity

interface DataNoteRepository {
    suspend fun getAll(): List<NotaEntity>
    suspend fun getAllFavs(): List<NotaEntity>
    suspend fun insert(nota: NotaEntity)
}