package com.charlye934.moviesjetpack.domain.artist

class ArtistInteractorImp(private val artistInteractor: ArtistInteractor) {
    suspend fun getArtist() = artistInteractor.getArtist()
    suspend fun updateArtist() = artistInteractor.updateArtsit()
}