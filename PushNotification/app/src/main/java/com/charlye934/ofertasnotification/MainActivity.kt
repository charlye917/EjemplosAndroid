package com.charlye934.ofertasnotification

import android.annotation.SuppressLint
import android.app.*
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.messaging.RemoteMessage

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_stat_name)
                .setContentTitle("Sistema Compras")
                .setContentText("Bienvenido")
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val chanelId = getString(R.string.normal_chanel_id)
            val chanelName = getString(R.string.normal_chanel_name)
            val chanel = NotificationChannel(chanelId, chanelName,NotificationManager.IMPORTANCE_DEFAULT)
            chanel.enableVibration(true)
            chanel.vibrationPattern = longArrayOf(100,200,50)

            notificationManager?.createNotificationChannel(chanel)
            notificationBuilder.setChannelId(chanelId)
        }

        notificationManager?.notify("", 0, notificationBuilder.build())
    }
}