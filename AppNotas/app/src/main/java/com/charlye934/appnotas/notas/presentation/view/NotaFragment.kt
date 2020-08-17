package com.charlye934.appnotas.notas.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.charlye934.appnotas.R
import com.charlye934.appnotas.notas.presentation.adapter.MyNotaRecyclerAdapter
import com.charlye934.appnotas.notas.presentation.listener.NotasListener
import com.charlye934.appnotas.notas.presentation.viewmodel.NotasViewModel
import kotlinx.android.synthetic.main.fragment_nota.*

class NotaFragment : Fragment() {

    private val notaViewModel: NotasViewModel by viewModels()
    private lateinit var notaLogin: NotasListener
    private var adapterNotas: MyNotaRecyclerAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_nota, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerNota.layoutManager = GridLayoutManager(requireContext(), 2)
        notaViewModel.getDataDB().observe(viewLifecycleOwner, Observer { list ->
            adapterNotas = MyNotaRecyclerAdapter(list,notaViewModel)
            recyclerNota.adapter = adapterNotas
        })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notaLogin = activity as DashboardActivity
    }

    companion object {
        val TAG = this::class.simpleName
    }
}