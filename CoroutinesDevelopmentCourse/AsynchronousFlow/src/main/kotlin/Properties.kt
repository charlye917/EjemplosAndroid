import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout

fun main(){
    runBlocking {
        val numbersFlow = sendNumbers()
        println("Flow hasn't started yed")
        println("Starting flow now")
        withTimeout(1000L) {
            numbersFlow.collect { println(it) }
        }
    }
}

fun sendNumbers() = //listOf(1,2,3).asFlow()
    ///flow{
    //listOf(1,2,3).forEach { emit(it) }
    flow {
        listOf(1,2,3).forEach {
            delay(400L)
            emit(it)
        }
    }
//}