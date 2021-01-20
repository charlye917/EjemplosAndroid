package com.charlye934.moviesjetpack.domain.repository

import com.charlye934.moviesjetpack.data.model.artist.Artist

interface ArtistRepository {
    suspend fun getArtist(): List<Artist>?
    suspend fun updateArtsit(): List<Artist>?
}