package com.charlye934.menucomidarealtimedatabase.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.menucomidarealtimedatabase.ComidaListActivity
import com.charlye934.menucomidarealtimedatabase.firebase.FunctionFirebase
import com.charlye934.menucomidarealtimedatabase.firebase.FunctionsFirebaseImp
import com.charlye934.menucomidarealtimedatabase.model.Comida
import com.charlye934.menucomidarealtimedatabase.util.ComonConstants
import com.example.menucomidarealtimedatabase.R
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.comida_list_content.view.*

class SimpleItemRecyclerViewAdapter : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.FoodViewHolder>() {

    private lateinit var mParentActivity: ComidaListActivity
    private val firebase: FunctionFirebase = FunctionsFirebaseImp()
    private var mValues = ArrayList<Comida>()
    private var mTwoPane: Boolean = false

    fun setData(comidaList: List<Comida>){
        mValues.clear()
        mValues.addAll(comidaList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.comida_list_content, parent, false)
        return FoodViewHolder(view)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val comidas = mValues[position]
        holder.bind(comidas, position)
        holder.itemView.tag = mValues[position]
        holder.itemView.setOnClickListener {  }
    }

    override fun getItemCount(): Int = mValues.size

    inner class FoodViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(comida: Comida, position: Int){
            view.id_text.text = comida.precio
            view.nombre.text = comida.nombre
            view.btnDelete.setOnClickListener {
               firebase.deleteFirebase(mValues[position])
            }
        }
    }
}