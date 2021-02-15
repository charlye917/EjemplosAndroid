package com.charlye934.menucomidarealtimedatabase.firebase

import android.content.Context
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.menucomidarealtimedatabase.adapter.SimpleItemRecyclerViewAdapter
import com.charlye934.menucomidarealtimedatabase.model.Comida

interface FunctionFirebase {
    fun deleteFirebase(comida: Comida)
    fun setUpRecycerView(context: Context, adapter: SimpleItemRecyclerViewAdapter)
    fun singleValueEvent(context: Context, textView: TextView)
    fun updateFirebase(comida:Comida)
}