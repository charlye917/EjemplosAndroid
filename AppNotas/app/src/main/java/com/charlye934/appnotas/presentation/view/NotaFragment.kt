package com.charlye934.appnotas.presentation.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.charlye934.appnotas.MainActivity
import com.charlye934.appnotas.R
import com.charlye934.appnotas.data.model.Nota
import com.charlye934.appnotas.domain.NotasInteractor
import com.charlye934.appnotas.presentation.adapter.MyNotaRecyclerAdapter
import com.charlye934.appnotas.presentation.listener.MainListener
import kotlinx.android.synthetic.main.fragment_nota.*
import java.util.*
import kotlin.collections.ArrayList

class NotaFragment : Fragment() {

    private lateinit var notaMain: MainListener
    private val ARG_COLUMN_COUNT = "column-count"
    private var mColumnCount = 2
    private var notaList: ArrayList<Nota>? = null
    private val adapterNotas: MyNotaRecyclerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if(arguments != null)
            mColumnCount = requireArguments().getInt(ARG_COLUMN_COUNT)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        notaList = ArrayList()
        notaList!!.add(Nota("Lista de la compra", "comprar pan tostado", true, android.R.color.holo_blue_light))
        notaList!!.add(Nota("Recordar", "He aparcado el coche en la calle República Argentina, no olvidarme de pagar en el parquímetro", false, android.R.color.holo_green_light))
        notaList!!.add(Nota("Cumpleaños (fiesta)", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", true, android.R.color.holo_orange_light))
        notaList!!.add(Nota("Lista de la compra", "comprar pan tostado", true, android.R.color.holo_blue_light))

        return inflater.inflate(R.layout.fragment_nota, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if(view.id == R.id.recyclerNota){
            recyclerNota.layoutManager = LinearLayoutManager(context)
        }else{
            var displayMetrics = this.resources.displayMetrics
            var dpWidth = displayMetrics.widthPixels / displayMetrics.density
            var numeroColumns = (dpWidth / 180) as Int

            recyclerNota.layoutManager = StaggeredGridLayoutManager(numeroColumns, StaggeredGridLayoutManager.VERTICAL)
        }

    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notaMain = activity as MainActivity

    }

    override fun onDetach() {
        super.onDetach()
    }

    companion object {
        val TAG = this::class.simpleName
    }
}