package com.charlye934.appnotas.notas.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.charlye934.appnotas.R
import com.charlye934.appnotas.notas.data.db.NotaEntity
import com.charlye934.appnotas.notas.presentation.adapter.MyNotaRecyclerAdapter
import com.charlye934.appnotas.notas.presentation.listener.NotasListener
import com.charlye934.appnotas.notas.presentation.viewmodel.NuevaNotaDialogViewModel
import kotlinx.android.synthetic.main.fragment_nota.*

class NotaFragment : Fragment() {

    private lateinit var notaLogin: NotasListener
    private var adapterNotas: MyNotaRecyclerAdapter? = null
    private lateinit var notaEntityList: List<NotaEntity>
    private val notaViewModel:NuevaNotaDialogViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        notaEntityList = arrayListOf()
        return inflater.inflate(R.layout.fragment_nota, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerNota.layoutManager = GridLayoutManager(requireContext(), 2)
        adapterNotas = MyNotaRecyclerAdapter(notaEntityList)
        recyclerNota.adapter = adapterNotas
        lanzarViewModel()
    }

    private fun lanzarViewModel(){
        notaViewModel.getAllNotas().observe(viewLifecycleOwner, Observer { data ->
            adapterNotas!!.setNewNotes(data)
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.accion_add_nota ->{
                mostrarDialogoNuevaNota()
                true
            }
            else  -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    private fun mostrarDialogoNuevaNota(){
        var fm = requireActivity().supportFragmentManager
        var dialogoNuevaNota = NuevaNotaDialogFragment()
        dialogoNuevaNota.show(fm, "NuevaNotaDialogoFragment")
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notaLogin = activity as DashboardActivity
    }

    companion object {
        val TAG = this::class.simpleName
    }
}