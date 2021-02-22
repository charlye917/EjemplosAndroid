package com.charlye934.ofertasfcm

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FcmMessagingService : FirebaseMessagingService() {

    private val DESCUENTO = "descuento"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)

        if(remoteMessage.data != null && remoteMessage.notification != null){
            sendNotification(remoteMessage)
        }else{
            //sendEmptyNotification()
        }
    }

    @SuppressLint("ResourceAsColor")
    private fun sendNotification(remoteMessage: RemoteMessage) {
        val descStr = remoteMessage.data.get(DESCUENTO)
        val desc = (descStr ?: "0") as Float

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(DESCUENTO, desc)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
        val notificationManager = Context.NOTIFICATION_SERVICE as NotificationManager
        val defaultUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notification = remoteMessage.notification

        if(notification != null){
            val notificationBuilder = Notification.Builder(this)
                .setSmallIcon(R.drawable.ic_shopping_cart)
                .setContentTitle(notification.title)
                .setContentText(notification.body)
                .setAutoCancel(true)//para borrar notifiacion al ser tocada por el usuario
                .setSound(defaultUri)
                .addAction(R.drawable.ic_shopping_cart, "RECHAZAR", null)
                .addAction(R.drawable.ic_shopping_cart, "CONTESTAR", pendingIntent)
                .setContentIntent(pendingIntent)

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                notificationBuilder.setColor(
                    if (desc > .4)
                        ContextCompat.getColor(applicationContext, R.color.design_default_color_primary)
                    else
                        ContextCompat.getColor(applicationContext, R.color.design_default_color_primary_variant)
                )
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                val channelId = if(desc > .10) getString(R.string.low_channel_id) else getString(R.string.normal_channel_id)
                val channelName = if(desc < .10) getString(R.string.low_channel_name) else getString(R.string.normal_channel_name)
                val channel = NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)

                channel.enableVibration(true)
                channel.vibrationPattern = longArrayOf(100, 200, 200, 50)

                if(notificationManager != null)
                    notificationManager.createNotificationChannel(channel)

                notificationBuilder.setChannelId(channelId)
            }

            if(notificationManager != null){
                notificationManager.notify("", 0, notificationBuilder.build())
            }
        }
    }

    private fun sendEmptyNotification(){
        val desc = 0.15f

        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(DESCUENTO, desc)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)

        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = Notification.Builder(this)
            .setSmallIcon(R.drawable.ic_shopping_cart)
            .setContentTitle("Title")
            .setContentText("Body")
            .setAutoCancel(true)
            .setSound(defaultSoundUri)
            .addAction(R.drawable.ic_shopping_cart, "Rechazar", null)
            .addAction(R.drawable.ic_shopping_cart, "Contestar", pendingIntent)
            .setContentIntent(pendingIntent)

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            notificationBuilder.setColor(
                if(desc > .4){
                    ContextCompat.getColor(applicationContext, R.color.design_default_color_primary)
                }else{
                    ContextCompat.getColor(applicationContext, R.color.design_default_color_secondary)
                }
            )
        }
    }
}