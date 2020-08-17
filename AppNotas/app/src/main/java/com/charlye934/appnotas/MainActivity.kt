package com.charlye934.appnotas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.charlye934.appnotas.data.model.Nota
import com.charlye934.appnotas.presentation.listener.MainListener
import com.charlye934.appnotas.presentation.view.LoginFragment
import com.charlye934.appnotas.presentation.view.NotaFragment
import com.charlye934.appnotas.presentation.view.NotasMainFragment

class MainActivity : AppCompatActivity(), MainListener {

    private var screenMain = R.id.frameMainActivity
    private var screenNotes = R.id.frameNotes

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        changeFragment(LoginFragment(), LoginFragment.TAG,screenMain)
    }

    override fun continueToNotasMain(){
        changeFragment(NotasMainFragment(), NotasMainFragment.TAG,screenMain)
    }

    override fun continueToNota() {
        changeFragment(NotaFragment(), NotaFragment.TAG,screenNotes)
    }

    private fun changeFragment(fragment:Fragment, tag: String?,screen:Int){
        supportFragmentManager.beginTransaction()
            .replace(screen, fragment)
            .addToBackStack(tag)
            .commit()
    }
}