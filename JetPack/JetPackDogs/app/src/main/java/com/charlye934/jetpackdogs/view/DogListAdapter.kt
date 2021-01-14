package com.charlye934.jetpackdogs.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.jetpackdogs.R
import com.charlye934.jetpackdogs.databinding.ItemDogBinding
import com.charlye934.jetpackdogs.model.db.DogBreed

class DogListAdapter : RecyclerView.Adapter<DogViewHolder>() {

    private var dogList: ArrayList<DogBreed> = arrayListOf()

    fun updateDogList(newDogList: List<DogBreed>){
        dogList.clear()
        dogList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemDogBinding>(layoutInflater, R.layout.item_dog, parent, false)
        return DogViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int = dogList.size
}

class DogViewHolder(var view: ItemDogBinding): RecyclerView.ViewHolder(view.root) {
    fun bindData(){
    }
}