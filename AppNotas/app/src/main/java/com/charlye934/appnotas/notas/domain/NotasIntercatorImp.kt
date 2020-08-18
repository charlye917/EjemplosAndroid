package com.charlye934.appnotas.notas.domain

import android.app.Application
import com.charlye934.appnotas.notas.data.db.NotaEntity
import com.charlye934.appnotas.notas.data.repository.DataNoteRepository
import com.charlye934.appnotas.notas.data.repository.DataNoteRepositoryImp

class NotasIntercatorImp(application: Application) : NotasInteractor {

    private val notasRepository: DataNoteRepository = DataNoteRepositoryImp(application)

    override suspend fun getAllNotes(): List<NotaEntity> {
        return notasRepository.getAll()
    }

    override suspend fun getAllFavoriteNotes(): List<NotaEntity> {
        return notasRepository.getAllFavs()
    }

    override suspend fun insert(nota: NotaEntity) {
       notasRepository.insert(nota)
    }


}