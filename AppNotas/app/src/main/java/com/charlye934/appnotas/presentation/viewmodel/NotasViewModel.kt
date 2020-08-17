package com.charlye934.appnotas.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.charlye934.appnotas.domain.NotasInteractor
import com.charlye934.appnotas.domain.NotasIntercatorImp

class NotasViewModel : ViewModel(){

    private val notasInteractor: NotasInteractor = NotasIntercatorImp()
}