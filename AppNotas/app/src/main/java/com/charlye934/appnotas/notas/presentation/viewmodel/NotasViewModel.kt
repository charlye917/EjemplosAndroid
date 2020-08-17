package com.charlye934.appnotas.notas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.appnotas.notas.data.model.NotaEntity
import com.charlye934.appnotas.notas.domain.NotasInteractor
import com.charlye934.appnotas.notas.domain.NotasIntercatorImp

class NotasViewModel : ViewModel(){

    private val notasInteractor: NotasInteractor = NotasIntercatorImp()

    fun editNoteClickNota(nota:NotaEntity){
        notasInteractor.editNotaClick(nota)
    }

    fun deleteNoteClick(nota:NotaEntity){
        notasInteractor.eliminaNota(nota)
    }

    fun favoriteNoteClick(nota:NotaEntity){
        notasInteractor.favoritaNota(nota)
    }

    fun getDataDB() = liveData{
        val response = notasInteractor.getData()
        emit(response)
    }


}