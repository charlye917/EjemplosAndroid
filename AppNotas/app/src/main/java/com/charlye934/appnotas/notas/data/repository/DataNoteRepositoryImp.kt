package com.charlye934.appnotas.notas.data.repository

import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.charlye934.appnotas.notas.data.db.NotaDao
import com.charlye934.appnotas.notas.data.db.NotaEntity
import com.charlye934.appnotas.notas.data.db.NotaRoomDatabase

class DataNoteRepositoryImp(context: Context) : DataNoteRepository {

    private val db: NotaRoomDatabase? = NotaRoomDatabase.getDataBase(context)
    private val notaDao = db!!.notaDao()
    private val allNotas = notaDao.getAll()
    private val allNotasFavoritas = notaDao.getAllFavorte()

    override suspend fun getAll(): LiveData<List<NotaEntity>> {
        return allNotas
    }

    override suspend fun getAllFavs(): LiveData<List<NotaEntity>> {
        return allNotasFavoritas
    }

    override suspend fun insert(nota: NotaEntity) {
        notaDao.insert(nota)
    }
}