package com.charlye934.menucomidarealtimedatabase.util

import android.util.Log
import com.charlye934.menucomidarealtimedatabase.model.Comida

object ComidaContent {
    val ITEMS = ArrayList<Comida>()
    val ITEM_MAP = HashMap<String, Comida>()
    val COUNT = 0

    fun addItem(item: Comida){
        ITEMS.add(item)
        ITEM_MAP.put(item.id, item)
    }

    fun updateItem(comida: Comida){
        ITEM_MAP[comida.id] = comida
        ITEMS.clear()
        ITEMS.addAll(ITEM_MAP.values)
        Log.d("__TAG UPDATE", ITEM_MAP.values.toString())
    }

    fun deleteItem(comida: Comida){
        ITEMS.remove(comida)
        ITEM_MAP.remove(comida)
    }
}
