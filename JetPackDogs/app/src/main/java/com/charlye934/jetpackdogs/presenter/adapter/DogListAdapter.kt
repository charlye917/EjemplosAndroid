package com.charlye934.jetpackdogs.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.jetpackdogs.R
import com.charlye934.jetpackdogs.data.model.DogBreed
import com.charlye934.jetpackdogs.databinding.ItemDogBinding
import com.charlye934.jetpackdogs.presenter.listener.DogClickListener

class DogListAdapter : RecyclerView.Adapter<DogListAdapter.DogViewHolder>() {

    private var dogList = arrayListOf<DogBreed>()

    fun updateDogList(newDogList: List<DogBreed>){
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val inflate = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemDogBinding>(inflate, R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.setData(holder, position)
    }

    override fun getItemCount(): Int = dogList.size

    inner class DogViewHolder(var view: ItemDogBinding): RecyclerView.ViewHolder(view.root){
        fun setData(hoder: DogViewHolder, position: Int){
            hoder.view.txtName.text = dogList[position].dogBreed
            hoder.view.txtlifeSpan.text = dogList[position].lifeSpan
        }
    }
}