package com.charlye934.appnotas.notas.domain

import com.charlye934.appnotas.notas.data.db.NotaEntity

interface NotasInteractor {
   suspend fun getAllNotes(): List<NotaEntity>
   suspend fun getAllFavoriteNotes():List<NotaEntity>
   suspend fun insert(nota: NotaEntity)
}