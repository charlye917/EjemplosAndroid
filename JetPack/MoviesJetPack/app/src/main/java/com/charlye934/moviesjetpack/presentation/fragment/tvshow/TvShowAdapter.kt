package com.charlye934.moviesjetpack.presentation.fragment.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.charlye934.moviesjetpack.R
import com.charlye934.moviesjetpack.data.model.tvshow.TvShow
import com.charlye934.moviesjetpack.databinding.ListItemBinding

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.MyViewHolderTvShow>() {

    private val tvShowList = ArrayList<TvShow>()

    fun setData(tvShows: List<TvShow>){
        tvShowList.clear()
        tvShowList.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderTvShow {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding: ListItemBinding = DataBindingUtil.inflate(
                layoutInflater,
                R.layout.list_item,
                parent,
                false
        )
        return MyViewHolderTvShow(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolderTvShow, position: Int) {
        holder.bind(tvShowList[position])
    }

    override fun getItemCount(): Int = tvShowList.size

    class MyViewHolderTvShow(private val binding: ListItemBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(tvShow: TvShow){
            binding.titleTextView.text = tvShow.name
            binding.descriptionTextView.text = tvShow.overview
            val posterUrl = "https://image.tmdb.org/t/p/w500" + tvShow.posterPath
            Glide.with(binding.imageView.context)
                    .load(posterUrl)
                    .into(binding.imageView)
        }
    }
}