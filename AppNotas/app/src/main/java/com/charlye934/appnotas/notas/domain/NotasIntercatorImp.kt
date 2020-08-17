package com.charlye934.appnotas.notas.domain

import com.charlye934.appnotas.notas.data.model.NotaEntity
import com.charlye934.appnotas.notas.data.repository.DataNoteRepository
import com.charlye934.appnotas.notas.data.repository.DataNoteRepositoryImp

class NotasIntercatorImp : NotasInteractor {

    private val notasRepository: DataNoteRepository = DataNoteRepositoryImp()

    override fun editNotaClick(nota: NotaEntity) {

    }

    override fun eliminaNota(nota: NotaEntity) {

    }

    override fun favoritaNota(nota: NotaEntity) {

    }

    override fun setData(nota: NotaEntity) {

    }

    override fun getData(): List<NotaEntity> {
        return notasRepository.getDataDB()
    }
}