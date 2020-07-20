package com.charlye934.jetpackdogs.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.jetpackdogs.R
import com.charlye934.jetpackdogs.databinding.ItemDogBinding
import com.charlye934.jetpackdogs.model.DogBreed
import com.charlye934.jetpackdogs.utils.getProgressDrawable
import com.charlye934.jetpackdogs.utils.loadImages
import kotlinx.android.synthetic.main.fragment_detail.view.*
import kotlinx.android.synthetic.main.item_dog.view.*

class DoglistAdapter(val dogsList:ArrayList<DogBreed>) : RecyclerView.Adapter<DoglistAdapter.DogViewHolder>() {

    fun updateDogsList(newDogList: List<DogBreed>){
        dogsList.clear()
        dogsList.addAll(newDogList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DogViewHolder{
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_dog, parent, false)
        val view = DataBindingUtil.inflate<ItemDogBinding>(inflater, R.layout.item_dog, parent, false)
        return DogViewHolder(view)
    }

    override fun getItemCount() = dogsList.size

    override fun onBindViewHolder(holder: DogViewHolder, position: Int) {
        holder.view.dog = dogsList[position]
        /*holder.name.text = dogsList[position].dogBreed
        holder.lifeSpan.text = dogsList[position].lifeSpan.toString()
        holder.view.setOnClickListener {
            val action = ListFragmentDogDirections.actionListFragmentDog2ActionDetailFragment()
            action.dogUuid = dogsList[position].uuid
            Navigation.findNavController(it).navigate(action)
        }
        holder.imgDog.loadImages(dogsList[position].imageUrl, getProgressDrawable(holder.imgDog.context))
         */
    }

    class DogViewHolder(var view: ItemDogBinding) : RecyclerView.ViewHolder(view.root)
}