package com.charlye934.jetpackdogs.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.charlye934.jetpackdogs.data.model.DogBreed

@Database(entities = [DogBreed::class], version = 1)
abstract class DogDatabase: RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object {
        @Volatile private var instace: DogDatabase? = null

        operator fun invoke(context: Context) = instace ?: synchronized(this){
            instace ?: buildDatabase(context).also {
                instace = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DogDatabase::class.java,
            "dogdatabase"
        ).build()
    }
}