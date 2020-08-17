package com.charlye934.appnotas.presentation.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import com.charlye934.appnotas.MainActivity
import com.charlye934.appnotas.R
import com.charlye934.appnotas.presentation.listener.MainListener
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var loginMain: MainListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnEnterLogin.setOnClickListener(this)
        Log.d(TAG, "ONVIEWCREATE")
    }

    override fun onClick(view: View?) {
       when(view!!.id){
           R.id.btnEnterLogin -> {
                loginMain.continueToNotasMain()
           }
       }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        loginMain = activity as MainActivity
    }

    companion object{
        val TAG = this::class.simpleName
    }
}