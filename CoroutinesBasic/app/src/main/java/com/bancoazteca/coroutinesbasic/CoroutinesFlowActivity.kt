package com.bancoazteca.coroutinesbasic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_coroutines_flow.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull

class CoroutinesFlowActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutines_flow)

        btnFlow.setOnClickListener {
            /*//show()
            runBlocking {
                runAsyncChronous().forEach{println(it)}
            }
            runBlocking {
                launch {
                    for(i in 1..3){
                        println("No estoy bloqueado $i")
                        delay(1000)
                    }
                }
                //collect no se ejecuta nada si el metodo collect no se llama, operador terminal
                fistFlow().collect { println(it) }
            }

            runBlocking {
                println("Llamando flow..")
                var flow = fistFlow()
                print("Collect...")
                flow.collect { println(it) }
                print("Collect again..")
                flow.collect{print(it)}
            }

            runBlocking {
                withTimeoutOrNull(2500){
                    fistFlow().collect{println(it)}
                }
                println("Finalizado")
            }
            runBlocking {
                //secondFlow().collect{println(it)}
                thirdFlow().collect{print(it)}
            }


            runBlocking {
                (1..3).asFlow()
                    .map{performRequest(it)}//recibe un flow de parametro y lo convierte en otra cosa
                    .collect{println(it)}//operador terminal
            }

            runBlocking {
                (1..3).asFlow()
                    .filter { it > 1 }//filtra los elementos
                    .map{performRequest(it)}
                    .collect{println(it)}
            }

            runBlocking {
                (1..3).asFlow()
                    .transform{//operador mas general por que podemos inviar a otros operadorse
                        emit("Makin request $it")
                        emit(performRequest(it))
                    }
                    .collect{
                        println(it)
                    }
            }

            runBlocking {
                (1..3).asFlow()
                    .take(2)//cancelar el flow cuando se alcance el limite establecido
                    .collect{
                        println(it)
                    }
            }

            runBlocking {
               var lista:List<Int> = (1..3).asFlow().toList()

            }

             */
            runBlocking {
                var numero = (6..90).asFlow()
                    .first()
                println(numero)
            }

            runBlocking {
                var resultado = (1..4).asFlow()
                    .reduce{ a,b->a+b}//1+2+3+4
                println(resultado)
            }

            //Flow secuencial
            runBlocking {
                (1..5).asFlow()
                    .filter {
                        println("filtrado $it")
                        it %2 == 0
                    }
                    .map {
                        println("Map $it")
                    }
                    .collect{
                        println("Collect it")
                    }
            }
        }
    }

    fun show(){ 
        //listar().forEach{ print(it) } 
        secuencia().forEach { print(it) }
    }

    fun listar():List<Int> = listOf(3,78,90)

    //Bloquea el hilo por 3 segundos
    fun secuencia(): Sequence<Int> = sequence {
        for(i in 1..3){
            Thread.sleep(1000)
            yield(i)//para enviar cada valor en squence
        }
    }

    suspend fun runAsyncChronous(): List<Int>{
        return runBlocking {
            delay(1000)
            return@runBlocking listOf(1,2,3)
        }
    }

    //flow call asincronos data string que se ejecuta de forma secuencial en la misma coroutina
    // y emite valores y finaliza con el estado completao o excepcion, flow necesita un constructor
    fun fistFlow(): Flow<Int> = flow{
        for(i in 1..3){
            delay(1000)
            emit(i)
        }
    }

    fun secondFlow():Flow<Int>{
        return  flowOf(1,2,3)
    }

    fun thirdFlow(): Flow<Int>{
        return (1..3).asFlow()
    }

    suspend fun performRequest(request:Int):String{
        delay(1000)
        return "response $request"

    }

}