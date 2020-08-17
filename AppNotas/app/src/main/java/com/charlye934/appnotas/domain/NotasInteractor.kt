package com.charlye934.appnotas.domain

import com.charlye934.appnotas.data.model.Nota

interface NotasInteractor {
    fun editNotaClick(nota:Nota)
    fun eliminaNota(nota: Nota)
    fun favoritaNota(nota:Nota)
}