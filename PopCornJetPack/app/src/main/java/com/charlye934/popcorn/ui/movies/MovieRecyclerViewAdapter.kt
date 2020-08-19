package com.charlye934.popcorn.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.popcorn.R
import com.charlye934.popcorn.retrofit.model.Movie
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieRecyclerViewAdapter: RecyclerView.Adapter<MovieRecyclerViewAdapter.MoviesViewHolder>(){

    private val mOnClickListener: View.OnClickListener = View.OnClickListener {
        val item = it.tag as Movie
    }
    private var movies:List<Movie> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie_list, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val item = movies[position]
        holder.tvTitle.text = item.title
        holder.tvRating.text = item.vote_average.toString()

        with(holder.view){
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    fun setData(popularMovies:List<Movie>){
        movies = popularMovies
        notifyDataSetChanged()
    }


    inner class MoviesViewHolder(val view:View): RecyclerView.ViewHolder(view) {
        val ivPhoto = view.image_view_photo
        val tvTitle = view.text_view_title
        val tvRating = view.text_view_rating
    }


}