package com.charlye934.moviesjetpack.presentation.fragment.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charlye934.moviesjetpack.R
import com.charlye934.moviesjetpack.data.model.movies.Movie
import com.charlye934.moviesjetpack.databinding.ListItemBinding
import kotlinx.android.synthetic.main.list_item.view.*

class MovieAdapter() : RecyclerView.Adapter<MovieAdapter.MyViewHolderMovie>() {

    private val movieList = ArrayList<Movie>()

    fun setList(movies: List<Movie>){
        movieList.clear()
        movieList.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderMovie {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.list_item,
                parent,
                false
        )
        return MyViewHolderMovie(binding)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MyViewHolderMovie, position: Int) {
        holder.bind(movieList[position])
    }

    inner class MyViewHolderMovie(private val binding: ListItemBinding):
            RecyclerView.ViewHolder(binding.root){

        fun bind(movie: Movie){
            binding.titleTextView.text = movie.title
            binding.descriptionTextView.text = movie.overview
            val posterURL = "https://image.tmdb.org/t/p/w500"+movie.posterPath
            Glide.with(binding.imageView.context)
                    .load(posterURL)
                    .into(binding.imageView)
        }
    }
}