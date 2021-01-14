package com.charlye934.jetpackdogs.model.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.charlye934.jetpackdogs.model.db.DogBreed

@Database(entities = [DogBreed::class], version = 1)
abstract class DogDataBase : RoomDatabase() {
    abstract fun dogDao(): DogDao

    companion object{
        @Volatile
        private var instance: DogDataBase? = null

        operator fun invoke(context: Context) = instance ?: synchronized(this){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DogDataBase::class.java,
            "dogdatabase"
        ).build()
    }
}