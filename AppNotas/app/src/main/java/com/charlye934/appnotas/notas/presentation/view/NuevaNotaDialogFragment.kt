package com.charlye934.appnotas.notas.presentation.view

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.charlye934.appnotas.R
import com.charlye934.appnotas.notas.data.db.NotaEntity
import com.charlye934.appnotas.notas.presentation.viewmodel.NuevaNotaDialogViewModel
import kotlinx.android.synthetic.main.fragment_nueva_nota_dialog.*

class NuevaNotaDialogFragment : DialogFragment() {

    private val notaViewModel: NuevaNotaDialogViewModel by activityViewModels()
    private var view = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity)

        builder.setTitle("Nueva nota")
            .setMessage("Introduzca los datos de la nueva app")
            .setPositiveButton("Guardar", DialogInterface.OnClickListener{ dialog, id ->

                var titulo = editTextTitulo.text.toString()
                var contenido = editTextContenido.text.toString()
                var esFavorita = switchNotaFavorita.isChecked
                var color = "azul"

                when(radioGroupColor.checkedRadioButtonId){
                    R.id.radioButtonColorRojo -> {color = "rojo"}
                    R.id.radioButtonColorVerde -> { color = "verde"}
                    R.id.radioButtonColorAzul -> { color = "azul"}
                }
                notaViewModel.insertNota(NotaEntity(titulo, contenido, esFavorita, color))
                dialog.dismiss()
            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener{ dialog, id ->
                dialog.dismiss()
            })

        val inflater = activity?.layoutInflater

        view = inflater!!.inflate(R.layout.fragment_nueva_nota_dialog, null) as Nothing?
        builder.setView(view)

        return builder.create()
    }

    companion object {
        val TAG = this::class.simpleName
    }
}