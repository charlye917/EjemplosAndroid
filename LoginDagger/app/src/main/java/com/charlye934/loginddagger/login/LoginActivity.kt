package com.charlye934.loginddagger.login

import android.os.Bundle
import android.telecom.Call
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.charlye934.loginddagger.R
import com.charlye934.loginddagger.http.TwitchAPI
import com.charlye934.loginddagger.http.pojo.Twitch
import com.charlye934.loginddagger.root.App
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Response
import javax.inject.Inject
import javax.security.auth.callback.Callback

class LoginActivity : AppCompatActivity(), LoginActivityMVP.View {
    
    @Inject
    lateinit var presenter: LoginActivityMVP.Presenter
    
    @Inject
    lateinit var twitchApi: TwitchAPI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).getComponet().inject(this)

        btnLogin.setOnClickListener {
            presenter.loginButtonClicked()
        }

        val call:retrofit2.Call<Twitch> = twitchApi.getTopGames("lejipm1mqporigkqljiipi5zuqby2m")
        call.enqueue(object : retrofit2.Callback<Twitch>{
            override fun onFailure(call: retrofit2.Call<Twitch>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: retrofit2.Call<Twitch>, response: Response<Twitch>) {
                response.body()!!.top.forEach {
                    Log.d("GAMES", it.toString())
                }
            }

        })
    }

    override var getFirstName: String = "Carlos"
    override var getLastName: String = "Arteaga"

    override fun showUserNotAvaible() {
        Toast.makeText(this, "Error el usuario no esta disponible", Toast.LENGTH_SHORT).show()

    }

    override fun showInputError() {
        Toast.makeText(this, "Error el nombre y apellido pueden estar vacio", Toast.LENGTH_SHORT).show()

    }

    override fun showUserSaved() {
        Toast.makeText(this, "Usuario guardado correctamente", Toast.LENGTH_SHORT).show()

    }

    override fun setFirstName(firstName: String) {
        etFirstName.setText(firstName)
    }

    override fun setLastName(lastName: String) {
        etFirstName.setText(lastName)
    }
}