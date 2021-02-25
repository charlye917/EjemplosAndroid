package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    private val mAunt: FirebaseAuth by lazy { FirebaseAuth.getInstance() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setOnClickListener()
    }

    private fun setOnClickListener(){
        buttonSignUp.setOnClickListener { checkData() }
        buttonGoLoginSignUp.setOnClickListener { goToLogin() }
    }

    private fun checkData(){
        val currentUser = mAunt.currentUser
        val user = editTextEmailSignUp.text.toString()
        val password = editTextPasswordSignUp.text.toString()
        val confirmPass = editTextConfirmPasswordSignUp.toString()

        if(user.isNotEmpty() && password.isNotEmpty() && confirmPass.isNotEmpty() && password == confirmPass){
            if(currentUser == null){
                Toast.makeText(this, "User is not logger", Toast.LENGTH_SHORT).show()
                createAccount(user, password)
            }else{
                Toast.makeText(this, "User is logger", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this, "Favor de llenar los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createAccount(email:String, password:String){
        mAunt.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("__TAG", "createUserWithEmail:success")
                    val user = mAunt.currentUser
                } else {
                    Log.w("__TAG", "createUserWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                    //updateUI(null)
                }
            }
    }

    private fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}