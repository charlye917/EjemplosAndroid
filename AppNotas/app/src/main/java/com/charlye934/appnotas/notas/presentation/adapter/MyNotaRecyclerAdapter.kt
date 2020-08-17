package com.charlye934.appnotas.notas.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.appnotas.R
import com.charlye934.appnotas.notas.data.model.NotaEntity
import com.charlye934.appnotas.notas.presentation.viewmodel.NotasViewModel
import kotlinx.android.synthetic.main.item_nota.view.*

class MyNotaRecyclerAdapter(private val items: List<NotaEntity>, private val notaViewModel:NotasViewModel) : RecyclerView.Adapter<MyNotaRecyclerAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota,parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.tvTitle.text = items[position].titulo
        holder.tvContenido.text = items[position].contenido

        if(items[position].favorito){
            holder.imgFavorita.setImageResource(R.drawable.ic_star_black_24dp)
        }

        holder.imgFavorita.setOnClickListener {
            notaViewModel.favoriteNoteClick(items[position])
        }
    }

    inner class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var tvTitle = view.tvTitleNota
        var tvContenido = view.tvContenidoNota
        var imgFavorita = view.imgFavoritaItemNota
    }
}