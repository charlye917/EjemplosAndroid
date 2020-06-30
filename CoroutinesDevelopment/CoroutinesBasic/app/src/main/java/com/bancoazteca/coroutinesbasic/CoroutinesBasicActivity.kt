package com.bancoazteca.coroutinesbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

class
CoroutinesBasicActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnMessage.setOnClickListener {
            //blockingExample()
            //suspendExample2()
            //dispatcher()
            //lauch()
            //exampleJob()
            //asyncAwait()
            //asyncAwaitDeferred()
            println(measureTimeMillis { withContextIO() }).toString()
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
    fun asyncAwait() = runBlocking {
        println(System.currentTimeMillis().toString())
        val num1: Int = async { calculateHard() }.await()
        println(System.currentTimeMillis().toString())
        val num2:Int = async { calculateHard() }.await()
        println(System.currentTimeMillis().toString())
        val resultado = num1 + num2
        println(resultado)
    }

    //Deferred futuro cancelable sin bloqueos, la forma de obtener su valor es atravez de un await
    fun asyncAwaitDeferred() = runBlocking{
        println(System.currentTimeMillis().toString())
        val numero1 = async { calculateHard() }
        println(System.currentTimeMillis().toString())
        val numero2 = async { calculateHard() }
        println(System.currentTimeMillis().toString())
        val result: Int = numero1.await() + numero2.await()//hasta aqui empeiza a espera el resutlado
        println(result.toString())
    }

    //otra forma de ver asyncAwait
    fun withContextIO() = runBlocking {
        val num1 = withContext(Dispatchers.IO){calculateHard()}
        val num2= withContext(Dispatchers.IO){calculateHard()}
        val res = num1 + num2
        println(res)
    }

    fun cancelCoroutine(){
        runBlocking {
            val job = launch {
                repeat(1000){
                    println("job $it")
                    delay(500L)
                }
            }
            delay(1400)
            job.cancel()
            println("main: cansado de esperar")
        }
    }
}