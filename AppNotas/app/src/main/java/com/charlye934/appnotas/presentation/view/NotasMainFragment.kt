package com.charlye934.appnotas.presentation.view

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.charlye934.appnotas.MainActivity
import com.charlye934.appnotas.R
import com.charlye934.appnotas.presentation.listener.MainListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_main_notas.*

class NotasMainFragment : Fragment() {

    private lateinit var notasMain: MainListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_notas, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigationButtonBar(bottomNavigationNotes)
    }

    private fun setupNavigationButtonBar(navigationBar: BottomNavigationView){
        navigationBar.setOnNavigationItemReselectedListener { item ->
            when(item.itemId){
                R.id.navigation_home -> { notasMain.continueToNota() }
                R.id.navigation_notifications -> {}
                R.id.navigation_dashboard -> {}
            }
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        notasMain = activity as MainActivity
    }

    companion object {
        val TAG = this::class.simpleName
    }
}