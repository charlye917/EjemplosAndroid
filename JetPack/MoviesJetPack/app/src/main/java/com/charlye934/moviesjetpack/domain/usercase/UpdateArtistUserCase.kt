package com.charlye934.moviesjetpack.domain.usercase

import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.domain.repository.ArtistRepository

class UpdateArtistUserCase (private val artistRepository: ArtistRepository){
    suspend fun execute(): List<Artist>? = artistRepository.updateArtsit()

}