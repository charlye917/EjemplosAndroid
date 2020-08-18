package com.charlye934.appnotas.notas.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.charlye934.appnotas.R
import com.charlye934.appnotas.notas.data.db.NotaEntity
import com.charlye934.appnotas.notas.presentation.viewmodel.NuevaNotaDialogViewModel
import kotlinx.android.synthetic.main.item_nota.view.*

class MyNotaRecyclerAdapter(private val items: List<NotaEntity>) : RecyclerView.Adapter<MyNotaRecyclerAdapter.NotesViewHolder>() {

    private var mValues = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_nota,parent, false)
        return NotesViewHolder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.tvTitle.text = mValues[position].titulo
        holder.tvContenido.text = mValues[position].contenido

        if(mValues[position].favorito){
            holder.imgFavorita.setImageResource(R.drawable.ic_star_black_24dp)
        }

        holder.imgFavorita.setOnClickListener {}
    }

    fun setNewNotes(nuevasNotas:List<NotaEntity>){
        mValues = nuevasNotas
        notifyDataSetChanged()
    }

    inner class NotesViewHolder(view:View) : RecyclerView.ViewHolder(view) {
        var tvTitle = view.tvTitleNota
        var tvContenido = view.tvContenidoNota
        var imgFavorita = view.imgFavoritaItemNota
    }
}