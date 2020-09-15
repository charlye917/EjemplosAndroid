package com.charlye934.movies.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charlye934.movies.R
import com.charlye934.movies.app.MyApp
import com.charlye934.movies.data.local.MovieEntity
import com.charlye934.movies.data.remote.ApiConstants
import kotlinx.android.synthetic.main.movie_item.view.*

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MoviesViewHolder>(){

    private var listMovies: List<MovieEntity> = arrayListOf()
    private var context: Context = MyApp.getContext()

    fun setData(listMovies: List<MovieEntity>){
        this.listMovies = listMovies
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = listMovies[position]
        val poster = holder.imgView

        Glide.with(context)
            .load(ApiConstants.IMAGE_API_PREFIX + item.posterPaht)
            .into(poster)
    }

    inner class MoviesViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val imgView = item.imgViewCoverItem
    }
}