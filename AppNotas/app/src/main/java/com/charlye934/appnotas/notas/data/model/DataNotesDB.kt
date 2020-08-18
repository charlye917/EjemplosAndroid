package com.charlye934.appnotas.notas.data.model

import com.charlye934.appnotas.notas.data.db.NotaEntity

interface DataNotesDB {
    fun getData():List<NotaEntity>
}