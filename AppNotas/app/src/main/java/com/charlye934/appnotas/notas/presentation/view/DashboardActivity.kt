package com.charlye934.appnotas.notas.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.charlye934.appnotas.R
import com.charlye934.appnotas.notas.presentation.listener.NotasListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_notas.*

class DashboardActivity : AppCompatActivity(), NotasListener {

    private val tagNotaFragment = NotaFragment.TAG

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notas)

        if(savedInstanceState == null){
            changeFragment(NotaFragment(), tagNotaFragment!!)
            setupNavigationButtonBar(bottomNavigationNotes)
        }
    }

    private fun setupNavigationButtonBar(navigationBar: BottomNavigationView){
        navigationBar.setOnNavigationItemReselectedListener { item ->
            when(item.itemId){
                R.id.navigation_home -> {  changeFragment(NotaFragment(), tagNotaFragment!!) }
                R.id.navigation_notifications -> {}
                R.id.navigation_dashboard -> {}
            }
        }
    }

    private fun changeFragment(fragment:Fragment, tag:String){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameNotes, fragment)
            .addToBackStack(tag)
            .commit()
    }
}