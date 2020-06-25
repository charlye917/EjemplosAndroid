package com.bancoazteca.countries.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bancoazteca.countries.R
import com.bancoazteca.countries.module.Country
import com.bancoazteca.countries.util.getProgressDrawable
import com.bancoazteca.countries.util.loadImage
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_country.view.*

class CountryListAdapter(var countries: ArrayList<Country>):RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>(){

    fun updateCountry(newCountries: List<Country>){
        countries.clear()
        countries.addAll(newCountries)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CountryViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent,false)
    )

    override fun getItemCount(): Int = countries.size

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    class CountryViewHolder(view:View): RecyclerView.ViewHolder(view){
        private val countryName = view.name
        private val imgView = view.imgView
        private val countryCapital = view.capital
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(country:Country){
            countryName.text = country.countryName
            countryCapital.text = country.capital
            imgView.loadImage(country.flag, progressDrawable)
        }
    }
}