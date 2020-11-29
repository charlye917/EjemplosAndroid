package com.example.rxjavaretrofit.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxjavaretrofit.R
import com.example.rxjavaretrofit.model.Contributor
import kotlinx.android.synthetic.main.list_item_contributor.view.*

class ContributorAdapter : RecyclerView.Adapter<ContributorAdapter.ContributorViewHolder>(){

    private var listContributor = listOf<Contributor>()

    fun setData(listContributor: List<Contributor>){
        this.listContributor = listContributor
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContributorViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_contributor, parent, false)
        return ContributorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ContributorViewHolder, position: Int) {
        val contributor = listContributor[position]
        holder.tvName.text = contributor.login
        holder.tvNumber.text = contributor.contributor.toString()
    }

    override fun getItemCount(): Int  = listContributor.size

    inner class ContributorViewHolder(item: View): RecyclerView.ViewHolder(item) {
        val tvName = item.tvName
        val tvNumber = item.tvNumber
    }

}