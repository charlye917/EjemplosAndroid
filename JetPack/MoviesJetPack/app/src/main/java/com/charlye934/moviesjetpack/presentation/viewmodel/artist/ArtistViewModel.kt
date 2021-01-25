package com.charlye934.moviesjetpack.presentation.viewmodel.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.charlye934.moviesjetpack.domain.artist.ArtistInteractor

class ArtistViewModel (private val artistInteractor: ArtistInteractor): ViewModel() {

    fun getArtist() = liveData{
        val result = artistInteractor.getArtist()
        emit(result)
    }

    fun updateArtist() = liveData {
        val result = artistInteractor.updateArtsit()
        emit(result)
    }
}