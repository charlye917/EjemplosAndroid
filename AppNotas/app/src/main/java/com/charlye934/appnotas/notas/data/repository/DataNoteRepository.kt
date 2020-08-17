package com.charlye934.appnotas.notas.data.repository

import com.charlye934.appnotas.notas.data.model.NotaDao
import com.charlye934.appnotas.notas.data.model.NotaEntity

interface DataNoteRepository {
    fun getDataDB():List<NotaEntity>
    fun setData(nota:NotaEntity)
}