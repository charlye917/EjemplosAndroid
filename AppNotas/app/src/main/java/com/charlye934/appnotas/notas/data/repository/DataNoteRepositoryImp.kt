package com.charlye934.appnotas.notas.data.repository

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.os.AsyncTask
import androidx.lifecycle.LiveData
import com.charlye934.appnotas.notas.data.db.NotaDao
import com.charlye934.appnotas.notas.data.db.NotaEntity
import com.charlye934.appnotas.notas.data.db.NotaRoomDatabase
import io.reactivex.schedulers.Schedulers

class DataNoteRepositoryImp(application: Application) : DataNoteRepository {

    private val db: NotaRoomDatabase? = NotaRoomDatabase.getDataBase(application!!)
    private val notaDao = db!!.notaDao()

    override suspend fun getAll(): List<NotaEntity>{
        return notaDao.getAll()
    }

    override suspend fun getAllFavs(): List<NotaEntity> {
        return notaDao.getAllFavorite()
    }

    override suspend fun insert(nota: NotaEntity) {
        notaDao.insert(nota)
    }
}