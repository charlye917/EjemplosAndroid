import kotlinx.coroutines.*

fun main(){
    println("Program execution will now block")
    runBlocking {
        launch {
            delay(2000L)
            println("Task from runBlocking")
        }

        GlobalScope.launch {
            delay(2000L)
            println("Task from GlobalScope")
        }

        coroutineScope {
            launch {
                delay(1500L)
                println("Task from coroutinesScoep")
            }
        }
    }
    println("Program execution will now continue")

}