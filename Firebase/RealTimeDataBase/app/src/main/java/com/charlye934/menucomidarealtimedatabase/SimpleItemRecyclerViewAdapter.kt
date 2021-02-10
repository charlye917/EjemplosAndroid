package com.charlye934.menucomidarealtimedatabase

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.comida_list_content.view.*

class SimpleItemRecyclerViewAdapter : RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.FoodViewHolder>() {

    private lateinit var mParentActivity: ComidaListActivity
    private lateinit var mValues: List<Comida>
    private lateinit var mTwoPane: Boolean
    

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

    class FoodViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun setData(view: View){
            val mIdView = view.id_text
            val mContentView = view.nombre
        }
    }
}