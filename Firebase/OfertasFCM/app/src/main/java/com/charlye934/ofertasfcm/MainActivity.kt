package com.charlye934.ofertasfcm

import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val SP_TOPICS = "sharedPreferencesTopics"
    private var mTopicsSet: Set<String>? = null
    private lateinit var mSharePreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        configSharedPreferences()

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

    private fun showTopics(){tvTopics.text = mTopicsSet.toString() }

    override fun onResume() {
        super.onResume()
        val manager = this.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.cancelAll()
    }

    private fun onViewClicked(view: View){
        val topic = resources.getStringArray(R.array.topicsValues)[spTopics.selectedItemPosition]

        when(view.id){
            R.id.btnSuscribir -> {
                if (!mTopicsSet.contains(topic)) {
                    FirebaseMessaging.getInstance().subscribeToTopic(topic)
                    mTopicsSet.add(topic)
                    savedSharedPreferences()
                }
            }

            R.id.btnDesuscribir -> {
                if (mTopicsSet.contains(topic)) {
                    FirebaseMessaging.getInstance().unsubscribeFromTopic(topic)
                    mTopicsSet.remove(topic)
                    savedSharedPreferences()
                }
            }
        }
    }

    private fun savedSharedPreferences() {
        val editor = mSharePreferences.edit()
        editor.clear()
        editor.putString(SP_TOPICS, mTopicsSet)
        editor.apply()

        showTopics()
    }
}