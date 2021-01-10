package com.charlye934.notificationdemo

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val channelId = "com.charlye934.notificationdemo1"
    private var notificationManager: NotificationManager? = null
    private val KEY_REPLEY = "key_replay"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        createNotificationChannel(channelId, "DemoChannel", "This is a demo")
        btnNotification.setOnClickListener {
            displayNotification()
        }
    }

    private fun displayNotification(){
        val notificationId = 45
        val tapResultIntent = Intent(this, SecondActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                tapResultIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
        )

        //reply action
        val remoteInput = RemoteInput.Builder(KEY_REPLEY).run{
            setLabel("Insert you name here")
                .build()
        }

        val replyAction = NotificationCompat.Action.Builder(
            0,
            "REPLY",
            pendingIntent
        ).addRemoteInput(remoteInput)
            .build()

        //action button 1
        val inent2 = Intent(this, DetailActivity::class.java)
        val pendingIntent2= PendingIntent.getActivity(
            this,
            0,
            inent2,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        val action2 = NotificationCompat.Action.Builder(0, "Details", pendingIntent2).build()

        //action button 2
        val inent3 = Intent(this, SettingsActivity::class.java)
        val pendingIntent3= PendingIntent.getActivity(
            this,
            0,
            inent3,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val action3 = NotificationCompat.Action.Builder(0, "Settings", pendingIntent3).build()

        val notification = NotificationCompat.Builder(this, channelId)
                .setContentTitle("Demo Title")
                .setContentText("This is a demo notification")
                .setSmallIcon(android.R.drawable.ic_dialog_info)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setStyle(NotificationCompat.BigTextStyle()
                    .bigText("This is a demo notificaiton...e"))
                //.setContentIntent(pendingIntent)
                .addAction(action2)
                .addAction(action3)
                .addAction(replyAction)
                .build()
        notificationManager?.notify(notificationId, notification)
    }

    private fun createNotificationChannel(id: String, name: String, channelDescription: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }

            notificationManager?.createNotificationChannel(channel)
        }
    }
}