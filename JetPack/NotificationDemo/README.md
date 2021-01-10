# Ejemplos de Notificacion

## Notification
<img src="1.gif" alt="drawing" width="300"/>

## Documentación

### Contenido de la notificación
Para comenzar, debes configurar el contenido y el canal de la notificación con un objeto NotificationCompat.Builder

Ten en cuenta que el constructor NotificationCompat.Builder requiere que proporciones un ID del canal, que se requiere por motivos de compatibilidad con Android 8.0 (API nivel 26) y versiones posteriores, pero se ignora en versiones anteriores.

```kotlin
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
```

### Crear un canal y definir la importancia

Debido a que debes crear el canal de notificación antes de publicar notificaciones en Android 8.0 y versiones posteriores, deberías ejecutar este código tan pronto como se inicie la app

Ten en cuenta que el constructor NotificationChannel requiere un importance, con una de las constantes de la clase NotificationManager. Este parámetro determina cómo interrumpir al usuario con cualquier notificación que pertenezca a este canal

```kotlin
createNotificationChannel(channelId, "DemoChannel", "This is a demo")

private fun createNotificationChannel(id: String, name: String, channelDescription: String){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel(id, name, importance).apply {
                description = channelDescription
            }

            notificationManager?.createNotificationChannel(channel)
        }
    }
```

### Establecer la acción de toque de la notificación
Cada notificación debería responder a un toque, por lo general para abrir una actividad en la app que se corresponda con la notificación. Para hacerlo, debes especificar un intent de contenido definido con un objeto PendingIntent y pasarlo a setContentIntent().

```kotlin
val tapResultIntent = Intent(this, SecondActivity::class.java)
val pendingIntent = PendingIntent.getActivity(
    this,
    0,
    tapResultIntent,
    PendingIntent.FLAG_UPDATE_CURRENT
)
```

### Agregar botones de acción
Una notificación puede ofrecer hasta tres botones de acción que le permitan al usuario responder de manera rápida, como posponer un recordatorio o incluso responder un mensaje de texto. Pero estos botones de acción no deben duplicar la acción realizada cuando el usuario presiona la notificación.

```kotlin
//action button 1
val inent2 = Intent(this, DetailActivity::class.java)
val pendingIntent2= PendingIntent.getActivity(
    this,
    0,
    inent2,
    PendingIntent.FLAG_UPDATE_CURRENT
)
val action2 = NotificationCompat.Action.Builder(0, "Details", pendingIntent2).build()
```

linlk: https://developer.android.com/training/notify-user/build-notification 


