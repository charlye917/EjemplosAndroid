package com.example.rxjavaretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.model.GithubRepo
import kotlinx.android.synthetic.main.list_item_repo.view.*

class RepositoryAdapter(): RecyclerView.Adapter<RepositoryAdapter.RepositroyViewHolder>(){

    private var repos = listOf<GithubRepo>()

    fun setData(repos: List<GithubRepo>){
        this.repos = repos
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositroyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_repo, parent, false)
        return RepositroyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepositroyViewHolder, position: Int) {
        val repo = repos[position]
        holder.tvRepositor.text = repo.name
        holder.tvLenguaje.text = repo.language
        holder.tvStars.text = repo.stargazers_count.toString()
    }

    override fun getItemCount(): Int = repos.size

    inner class RepositroyViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val tvRepositor = item.tvRepositorio
        val tvLenguaje = item.tvLenguaje
        val tvStars = item.tvStars
    }

}