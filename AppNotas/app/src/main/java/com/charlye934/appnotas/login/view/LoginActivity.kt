package com.charlye934.appnotas.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.charlye934.appnotas.R
import com.charlye934.appnotas.login.listener.LoginListener
import com.charlye934.appnotas.notas.presentation.view.DashboardActivity

class LoginActivity : AppCompatActivity(), LoginListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        if(savedInstanceState == null)
            changeFragment(LoginFragment(), LoginFragment.TAG)
    }

    override fun continueToNotasActivity(){
        val intent = Intent(this, DashboardActivity::class.java)
        startActivity(intent)
    }

    private fun changeFragment(fragment:Fragment, tag: String?){
        supportFragmentManager.beginTransaction()
            .replace(R.id.frameMainActivity, fragment)
            .addToBackStack(tag)
            .commit()
    }

    override fun onBackPressed() {
        when(supportFragmentManager .fragments.last()){
            is LoginFragment -> { finish() }
        }
        //super.onBackPressed()
    }
}