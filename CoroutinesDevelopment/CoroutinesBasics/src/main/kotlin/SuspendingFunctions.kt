import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

var funcionCalls = 0

fun main(){
    GlobalScope.launch { completeMessage() }
    GlobalScope.launch { improveMessage() }
    print("Hello, ")
    Thread.sleep(2000L)
    println("There have been $funcionCalls calls so far")
}

suspend fun completeMessage(){
    delay(500L)
    println("World")
    funcionCalls++
}

suspend fun improveMessage(){
    delay(1000L)
    println("Suspend function are cool")
    funcionCalls++
}