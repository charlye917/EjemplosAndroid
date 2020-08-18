package com.charlye934.appnotas

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.RadioGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import kotlinx.android.synthetic.main.fragment_nueva_nota_dialog.*

class NuevaNotaDialogFragment : DialogFragment() {

    private val notaViewModel:NuevaNotaDialogViewModel by viewModels()
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
            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener{ dialog, id -> })

        val inflater = activity?.layoutInflater

        view = inflater!!.inflate(R.layout.fragment_nueva_nota_dialog, null) as Nothing?
        builder.setView(view)

        return builder.create()
    }

    companion object {
        val TAG = this::class.simpleName
    }
}