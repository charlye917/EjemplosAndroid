package com.charlye934.appnotas.notas.data.repository

import com.charlye934.appNotaEntitys.NotaEntitys.data.db.DataNotesDBImp
import com.charlye934.appnotas.notas.data.db.DataNotesDB
import com.charlye934.appnotas.notas.data.model.NotaDao
import com.charlye934.appnotas.notas.data.model.NotaEntity

class DataNoteRepositoryImp : DataNoteRepository {

    private val dataNotesDb: DataNotesDB = DataNotesDBImp()

    override fun getDataDB(): List<NotaEntity> {
        return dataNotesDb.getData()
    }

    override fun setData(nota: NotaEntity) {

    }
}