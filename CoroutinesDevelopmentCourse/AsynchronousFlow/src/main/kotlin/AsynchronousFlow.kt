import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        println("Reciving prime numbers")
        sendPrimes().collect {
            println("Recibe primer nombre $it")
        }
        println("Finished reciving numbers")
    }

}

fun sendPrimes(): Flow <Int> = flow{
    val primesList = listOf(2,3,5,7,9,1,3,2,4,6,8)
    primesList.forEach {
        delay(it * 100L)
        emit(it)
    }
}