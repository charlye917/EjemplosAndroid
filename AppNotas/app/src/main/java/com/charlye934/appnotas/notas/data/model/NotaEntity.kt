package com.charlye934.appnotas.notas.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notas")
data class NotaEntity(
    val titulo:String?,
    val contenido:String?,
    val favorito:Boolean,
    val color: Int
){
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0
}