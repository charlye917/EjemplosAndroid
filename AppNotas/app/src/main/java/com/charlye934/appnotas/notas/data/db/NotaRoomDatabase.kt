package com.charlye934.appnotas.notas.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NotaEntity::class], version = 1)
abstract class NotaRoomDatabase : RoomDatabase() {
    abstract fun notaDao(): NotaDao

    companion object{
        private var INSTANCE: NotaRoomDatabase? = null
        fun getDataBase(context: Context): NotaRoomDatabase? {
            if (INSTANCE == null) {
                synchronized(NotaRoomDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context.applicationContext,
                            NotaRoomDatabase::class.java, "notas_database"
                        ).build()
                    }
                }
            }
            return INSTANCE
        }
    }

}