package com.charlye934.moviesfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.moviesfeed.movies.ViewModel
import kotlinx.android.synthetic.main.move_list_item.view.*

class ListAdapter(private val list: List<ViewModel>) : RecyclerView.Adapter<ListAdapter.MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.move_list_item, parent, false)
        return MovieViewHolder(item)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.name.text = list[position].nameMovie
        holder.country.text = list[position].countryMovie
    }

    inner class MovieViewHolder (view:View): RecyclerView.ViewHolder(view){
        var name:TextView = view.tvFragmentTitle
        var country:TextView = view.tvFragmentCountry
    }
}


