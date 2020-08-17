package com.charlye934.appnotas.login.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.charlye934.appnotas.R
import com.charlye934.appnotas.login.listener.LoginListener
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private lateinit var loginLogin: LoginListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEnterLogin.setOnClickListener{ loginLogin.continueToNotasActivity() }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        loginLogin = activity as LoginActivity
    }

    companion object{
        val TAG = this::class.simpleName
    }
}