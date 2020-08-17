package com.charlye934.appnotas.notas.data.db

import com.charlye934.appnotas.notas.data.model.NotaDao
import com.charlye934.appnotas.notas.data.model.NotaEntity

interface DataNotesDB {
    fun getData():List<NotaEntity>
}