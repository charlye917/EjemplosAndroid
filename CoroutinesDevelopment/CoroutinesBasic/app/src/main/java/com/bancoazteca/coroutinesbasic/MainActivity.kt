package com.bancoazteca.coroutinesbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMessage.setOnClickListener {
            //blockingExample()
            //suspendExample2()
            //dispatcher()
            //lauch()
            //exampleJob()
            asyncAwait()
            Toast.makeText(this,"Prueba mensaje",Toast.LENGTH_SHORT).show()
        }
    }

    private fun blockingExample(){
        println("Tarea 1: " + Thread.currentThread().name)
        longTaskWithMessage("Tarea 2")
        println("Tarea 3" + Thread.currentThread().name)
    }

    //Funcion que bloquea el hilo de ejecucion actual
    private fun longTaskWithMessage(message: String){
        Thread.sleep(5000)
        println(message + Thread.currentThread().name)
    }

    //Funcion que suspende (pausar y reanudar posteriormete) el hilo, pero no lo bloquea
    private suspend fun delayCoroutine(message: String){
        delay(4000)
        println(message + Thread.currentThread().name)
    }

    //runblocking nos permite ejecutar una nueva corrutina y bloquear el hilo actual de forma interrupida hasta su finalizacion en orden
    fun suspendExample(){
        println("Tarea 1: " + Thread.currentThread().name)
        runBlocking { delayCoroutine("Tarea 2") }
        println("Tarea 3" + Thread.currentThread().name)
    }

    //Segunda forma de usar runblocking
    fun suspendExample2() = runBlocking{
        println("Tarea 1: " + Thread.currentThread().name)
        delayCoroutine("Tarea 2")
        println("Tarea 3" + Thread.currentThread().name)
    }

    //Dispatcher indicaremos en que hilo se va a ejecutar la coroutina
    fun dispatcher(){
        runBlocking {
            println("Hilo en el que se ejecuta 1: ${Thread.currentThread().name}")
        }

        //Unconfined no nos importa el hilo en el que se ejecute
        runBlocking(Dispatchers.Unconfined) {
            println("Hilo en el que se ejecuta 2: ${Thread.currentThread().name}")
        }

        //Default para operaciones que hacen uso intencivo de la cpu
        runBlocking(Dispatchers.Default) {
            println("Hilo en el que se ejecuta 3: ${Thread.currentThread().name}")
        }

        //IO hilo para operaciones de entrada y salida de datos, para webservice u operaciones en base de datos
        runBlocking(Dispatchers.IO){
            println("Hilo en el que se ejecuta 4: ${Thread.currentThread().name}")
        }

        //Creacion de un nuevo hilo que nosostros queramos
        runBlocking(newSingleThreadContext("MiHilo")) {
            println("Hilo en el que se ejecuta 5: ${Thread.currentThread().name}")
        }
    }

    //Launch ejecuta una nueva coroutina sin bloquear el hilo actual lanzandonos una referencia de la corrutina como job, no devuelve resultado
    fun lauch(){
        println("Tarea 1: " + Thread.currentThread().name)
        //Nos ayuda a definir el ciclo de vida de la coroutina
        GlobalScope.launch { delayCoroutine("Tarea 2") }
        println("Tarea 3" + Thread.currentThread().name)
    }

    //job es un elemento cancelable con un ciclo de vida que culmina a su finalizacion, no devuelve nada
    fun exampleJob(){
        println("Tarea 1" + Thread.currentThread().name)
        val job = GlobalScope.launch {
            delayCoroutine("Tarea 2")
        }
        println("Tarea 2" + Thread.currentThread().name)
        job.cancel()
    }

    suspend fun calculateHard():Int{
        delay(2000)
        return 15
    }

    //async cuando deseamos ejecutar algo y deseamos esperar resultado
    fun asyncAwait() = runBlocking{
        println(System.currentTimeMillis().toString())
        async { calculateHard() }.await()
        async { calculateHard() }.await()
    }
}