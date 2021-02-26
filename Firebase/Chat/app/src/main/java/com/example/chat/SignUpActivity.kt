package com.example.chat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chat.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {

    private val mAunt: FirebaseAuth by lazy { FirebaseAuth.getInstance() }
    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setOnClickListener()
    }

    private fun setOnClickListener(){
        with(binding){
            buttonSignUp.setOnClickListener { checkData() }
            buttonGoLoginSignUp.setOnClickListener { goToLogin() }
        }
    }

    private fun checkData(){
        val email = binding.editTextEmailSignUp.text.toString().trim()
        val password = binding.editTextPasswordSignUp.text.toString().trim()

        if(validateData(email, password)){
            createAccount(email, password)
        }else{
            Toast.makeText(this, "Favor de llenar los campos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createAccount(email: String, password: String){
        mAunt.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    mAunt.currentUser!!.sendEmailVerification().addOnCompleteListener(this) {
                        Toast.makeText(this, "An email has been sent to you. Please, confirm before sign in.", Toast.LENGTH_SHORT).show()
                        goToLogin()
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
                    }
                } else {
                    Toast.makeText(baseContext, "Authentication failed.", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun goToLogin(){
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    private fun validateData(email: String, password: String): Boolean{
        return email.isNotEmpty()
                && password.isNotEmpty()
                && password == binding.editTextConfirmPasswordSignUp.text.toString()
    }
}