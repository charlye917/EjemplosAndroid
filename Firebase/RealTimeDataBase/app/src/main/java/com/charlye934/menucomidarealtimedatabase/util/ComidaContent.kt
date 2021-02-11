package com.charlye934.menucomidarealtimedatabase.util

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
        ITEMS.set(ITEMS.indexOf(comida), comida)
        ITEM_MAP.put(comida.id, comida)
    }

    fun deleteItem(comida: Comida){
        ITEMS.remove(comida)
        ITEM_MAP.remove(comida)
    }
}