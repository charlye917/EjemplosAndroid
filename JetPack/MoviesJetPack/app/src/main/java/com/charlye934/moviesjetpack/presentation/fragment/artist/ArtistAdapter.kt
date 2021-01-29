package com.charlye934.moviesjetpack.presentation.fragment.artist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charlye934.moviesjetpack.R
import com.charlye934.moviesjetpack.data.model.artist.Artist
import com.charlye934.moviesjetpack.databinding.ListItemBinding
import com.charlye934.moviesjetpack.presentation.fragment.movie.MovieAdapter
import kotlinx.android.synthetic.main.list_item.view.*

class ArtistAdapter : RecyclerView.Adapter<ArtistAdapter.MyViewHolderArtist>() {

    private val artistList = ArrayList<Artist>()

    fun setList(artists: List<Artist>){
        artistList.clear()
        artistList.addAll(artists)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderArtist {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.list_item,
            parent,
            false
        )
        return MyViewHolderArtist(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolderArtist, position: Int) {
        holder.bind(artistList[position])
    }

    override fun getItemCount() = artistList.size

    inner class MyViewHolderArtist(private val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root){

        fun bind(artist: Artist){
            binding.titleTextView.text = artist.name
            binding.descriptionTextView.text = artist.popularity.toString()
            val posterUrl = "https://image.tmdb.org/t/p/w500"+artist.profilePath
            Glide.with(binding.imageView.context)
                .load(posterUrl)
                .into(binding.imageView)
        }

    }
}