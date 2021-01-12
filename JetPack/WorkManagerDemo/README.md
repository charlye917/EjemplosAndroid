# WorkManager
WorkManager es una API que facilita la programación de tareas asíncronas y diferibles que se deben ejecutar incluso si se cierra la app o se reinicia el dispositivo.

## Funciones
Además de brindar una API más simple y coherente, WorkManager tiene otros beneficios clave, como los siguientes:

    - Restricciones de trabajos: 
Mediante las restricciones de trabajo, define de manera declarativa las condiciones óptimas que necesita tu trabajo para ejecutarse. (Por ejemplo, haz que se ejecute solo cuando el dispositivo esté conectado a una red Wi-Fi, esté inactivo, tenga suficiente espacio de almacenamiento, etcétera).

    - Programación sólida
WorkManager te permite programar un trabajo para que se ejecute una sola vez o periódicamente con ventanas de programación flexibles. También puedes etiquetar los trabajos y asignarles un nombre, lo que te permitirá programar trabajos únicos y reemplazables, así como supervisar o cancelar grupos de trabajo en forma conjunta. El trabajo programado se almacena en una base de datos SQLite administrada de forma interna, y WorkManager se encarga de garantizar que ese trabajo se conserve y se reprograme después de todos los reinicios del dispositivo. Además, WorkManager cumple con las funciones de ahorro de energía y las prácticas recomendadas, como el modo Descanso, para que no tengas que preocuparte por eso.

    - Política de reintento flexible
A veces, se producen errores en el trabajo. WorkManager ofrece políticas de reintento flexibles que incluyen una política de retirada exponencial configurable.

    - Encadenamiento de trabajos
Si tienes trabajos complejos que están relacionados, encadena las tareas individuales mediante una interfaz fluida y natural que te permite controlar qué partes se ejecutan de forma secuencial y cuáles en paralelo.

## Liga Documentacion
[Documentacion de la libreria WorkManager] (https://developer.android.com/topic/libraries/architecture/workmanager/basics)
https://developer.android.com/topic/libraries/architecture/workmanager/

