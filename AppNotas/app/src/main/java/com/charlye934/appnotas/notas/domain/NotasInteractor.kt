package com.charlye934.appnotas.notas.domain

import com.charlye934.appnotas.notas.data.db.NotaEntity

interface NotasInteractor {
    fun editNotaClick(nota: NotaEntity)
    fun eliminaNota(nota: NotaEntity)
    fun favoritaNota(nota: NotaEntity)
    fun setData(nota: NotaEntity)
    fun getData():List<NotaEntity>
}