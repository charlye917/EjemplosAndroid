package com.charlye934.ofertasfcm

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.InstanceIdResult

class MainActivity : AppCompatActivity() {

    private val SP_TOPICS = "sharedPreferencesTopics"
    private lateinit var mTopicsSet: Set<String>
    private lateinit var mSharePreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //configSharedPreferences()

        FirebaseInstanceId.getInstance()
                .instanceId
                .addOnSuccessListener {
                    Log.d("tokenId", it.token)
                }
    }

    private fun configSharedPreferences() {
        mSharePreferences = getPreferences(Context.MODE_PRIVATE)
        mTopicsSet = mSharePreferences.getStringSet(SP_TOPICS, HashSet<String>())!!
        showTopics()
    }

    private fun showTopics(){ }
}