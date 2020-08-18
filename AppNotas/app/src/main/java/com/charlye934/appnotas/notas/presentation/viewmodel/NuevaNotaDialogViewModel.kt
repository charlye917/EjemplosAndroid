package com.charlye934.appnotas.notas.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.charlye934.appnotas.notas.data.db.NotaEntity
import com.charlye934.appnotas.notas.data.repository.DataNoteRepository
import com.charlye934.appnotas.notas.data.repository.DataNoteRepositoryImp
import com.charlye934.appnotas.notas.domain.NotasInteractor
import com.charlye934.appnotas.notas.domain.NotasIntercatorImp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class NuevaNotaDialogViewModel(application: Application) : ViewModel() {

    private var notaRepository: NotasInteractor = NotasIntercatorImp(application)

    fun getAllNotas() = liveData {
        val response = notaRepository.getAllNotes()
        emit(response)
    }

    fun getAllFavoriteNotas() = liveData{
        val response = notaRepository.getAllFavoriteNotes()
        emit(response)
    }

    fun insertNota(nuevaNotaEntity: NotaEntity){
        GlobalScope.launch {
            notaRepository.insert(nuevaNotaEntity)
        }
    }
}