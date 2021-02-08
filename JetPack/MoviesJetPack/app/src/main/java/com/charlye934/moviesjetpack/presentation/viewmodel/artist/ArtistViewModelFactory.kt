package com.charlye934.moviesjetpack.presentation.viewmodel.artist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.charlye934.moviesjetpack.domain.artist.ArtistInteractor

class ArtistViewModelFactory(private val artistInteractor: ArtistInteractor): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ArtistViewModel(artistInteractor) as T
    }
}