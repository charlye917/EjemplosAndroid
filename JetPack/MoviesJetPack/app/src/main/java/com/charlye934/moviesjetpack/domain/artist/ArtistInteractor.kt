package com.charlye934.moviesjetpack.domain.artist

import com.charlye934.moviesjetpack.data.model.artist.Artist

interface ArtistInteractor {
    suspend fun getArtist(): List<Artist>?
    suspend fun updateArtsit(): List<Artist>?
}