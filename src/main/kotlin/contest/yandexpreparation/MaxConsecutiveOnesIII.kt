package contest.yandexpreparation

import kotlinx.coroutines.*

//suspend fun main() = withContext(Dispatchers.IO) {
//    for (i in 1..10) {
//        launch {
//            delay(100)
//            println(i)
//        }
//    }
//
//    println("asf")
//}

suspend fun someFun(num: Int): Int {
    var counter = 0
    println("Before delay")
    ++counter
    delay(1000)
    println("After delay")
    println(num)
    ++counter
    delay(1000)
    anotherFun()
    return 2
}

suspend fun anotherFun() {
    delay(100)
}
