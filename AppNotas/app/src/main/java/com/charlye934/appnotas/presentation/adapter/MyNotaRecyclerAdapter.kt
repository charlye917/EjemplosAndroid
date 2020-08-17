package com.charlye934.appnotas.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.appnotas.R
import com.charlye934.appnotas.data.model.Nota
import com.charlye934.appnotas.domain.NotasInteractor
import kotlinx.android.synthetic.main.item_nota.view.*

class MyNotaRecyclerAdapter(private val items:List<Nota>, private val listener:NotasInteractor) : RecyclerView.Adapter<MyNotaRecyclerAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota,parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.tvTitle.text = items[position].titulo
        holder.tvContenido.text = items[position].contenido

        if(items[position].favorita){
            holder.imgFavorita.setImageResource(R.drawable.ic_star_black_24dp)
        }

        holder.imgFavorita.setOnClickListener {
            if(listener != null){
                listener.favoritaNota(items[position])
            }
        }
    }

    inner class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var tvTitle = view.tvTitleNota
        var tvContenido = view.tvContenidoNota
        var imgFavorita = view.imgFavoritaItemNota
    }
}