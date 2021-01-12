# WorkManager

WorkManager es una biblioteca que se utiliza para poner en cola el trabajo diferible que se garantiza que se ejecutará en algún momento después de que se cumplan sus restricciones. WorkManager permite la observación del estado del trabajo y la capacidad de crear cadenas de trabajo complejas.

Los trabajos se definen con la clase Worker. El método doWork() se ejecuta de forma asíncrona en un subproceso en segundo plano proporcionado por WorkManager.

El Result que muestra doWork() le informa al servicio de WorkManager si el trabajo se ejecutó correctamente y, en caso de haber un error, si se debe reintentar o no.

    - Result.success(): el trabajo se completó correctamente.
    - Result.failure(): no se pudo completar el trabajo.
    - Result.retry(): no se pudo completar el trabajo y se debe volver a intentar en otro momento según su política de reintento.

## Liga Documentacion
[Documentacion de la libreria WorkManager] (https://developer.android.com/topic/libraries/architecture/workmanager/basics)

