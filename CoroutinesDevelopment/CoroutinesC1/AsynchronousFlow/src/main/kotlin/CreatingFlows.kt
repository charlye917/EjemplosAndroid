import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking

fun main(){
    runBlocking {
        sendNumber().collect{
            println("Recived $it")
        }
    }
}

fun sendNumber()
    = flowOf("One","Two","Three")
//= listOf(1,2,3).asFlow()
        /*= flow<Int>{
        for(i in 1..10){
            emit(i)
    }
}*/