package com.spike.kotlin.c14_coroutines

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext
import kotlin.time.Duration.Companion.milliseconds

class Credentials
class UserID
class UserData

suspend fun login(credentials: Credentials): UserID = UserID()
suspend fun loadUserData(userID: UserID): UserData = UserData()
fun showData(data: UserData) {}

suspend fun showUserData(credentials: Credentials) {
    val userID = login(credentials)
    val userData = loadUserData(userID)
    showData(userData)
}

// Suspend function 'suspend fun showUserData(credentials: Credentials): Unit' can only be called from a coroutine or another suspend function.
//fun main() {
//    showUserData(Credentials())
//}

// =============================================================================
// coroutine builders
// =============================================================================
// From regular code into the realm of coroutines: The [runBlocking] function

suspend fun doSomethingSlowly() {
    delay(500.milliseconds)
    println("I'm done")
}


// Creating start-and-forget coroutines: The [launch] function

private var zeroTime = System.currentTimeMillis()
fun log(message: Any?) = println("${System.currentTimeMillis() - zeroTime} [${Thread.currentThread().name}] $message")

// Awaitable computations: The [async] builder

suspend fun slowlyAddNumbers(a: Int, b: Int): Int {
    log("Waiting a bit before calculating $a + $b")
    delay(100.milliseconds * a)
    return a + b
}

// =============================================================================
// dispatchers
// =============================================================================

// =============================================================================
// CoroutineContext
// =============================================================================

suspend fun introspect() {
    log(coroutineContext)
}

// VM Options: -Dkotlinx.coroutines.debug
fun main() {
//    runBlocking {
//        doSomethingSlowly()
//    }
//    // I'm done

//    runBlocking {
//        log("The first, parent, coroutine starts")
//        // [launch] return [kotlinx.coroutines.Job]
//        launch {
//            log("The second coroutine starts and is ready to suspended")
//            delay(100.milliseconds)
//            log("The second coroutine starts and is resumed")
//        }
//        launch {
//            log("The third coroutine can run in the meantime")
//        }
//        log("The first coroutine has launched two more coroutine")
//    }
//    // 88 [main @coroutine#1] The first, parent, coroutine starts
//    //102 [main @coroutine#1] The first coroutine has launched two more coroutine
//    //105 [main @coroutine#2] The second coroutine starts and is ready to suspended
//    //116 [main @coroutine#3] The third coroutine can run in the meantime
//    //214 [main @coroutine#2] The second coroutine starts and is resumed

//    runBlocking {
//        log("Starting the async computation")
//        val myFirstDeferred = async { slowlyAddNumbers(2, 2) }
//        val mySecondDeferred = async { slowlyAddNumbers(4, 4) }
//        log("Waiting for the deferred value to be available")
//        log("The first result: ${myFirstDeferred.await()}")
//        log("The second result: ${mySecondDeferred.await()}")
//        // 76 [main @coroutine#1] Starting the async computation
//        // 91 [main @coroutine#1] Waiting for the deferred value to be available
//        //103 [main @coroutine#2] Waiting a bit before calculating 2 + 2
//        //109 [main @coroutine#3] Waiting a bit before calculating 4 + 4
//        //323 [main @coroutine#1] The first result: 4
//        //511 [main @coroutine#1] The second result: 8
//    }

//    runBlocking {
//        log("Doing some work")
//        launch(Dispatchers.Default) {
//            log("Doing some background work")
//        }
//    }
//    //541 [main @coroutine#1] Doing some work
//    //745 [DefaultDispatcher-worker-1 @coroutine#2] Doing some background work

//    runBlocking {
//        log("Doing some work")
//        launch() {
//            log("Doing some background work")
//            withContext(Dispatchers.Default) {
//                log("Doing some background work further")
//            }
//        }
//    }
//    // 939 [main @coroutine#1] Doing some work
//    //1049 [main @coroutine#2] Doing some background work
//    //1084 [DefaultDispatcher-worker-2 @coroutine#2] Doing some background work further

    // CoroutineContext
    runBlocking {
        introspect()
    }
    // 104 [main @coroutine#1] [CoroutineId(1), "coroutine#1":BlockingCoroutine{Active}@16b4a017, BlockingEventLoop@8807e25]
    runBlocking(Dispatchers.IO + CoroutineName("Coolroutine")) {
        introspect()
    }
    // 124 [DefaultDispatcher-worker-1 @Coolroutine#2] [CoroutineName(Coolroutine), CoroutineId(2), "Coolroutine#2":BlockingCoroutine{Active}@3395876, Dispatchers.IO]

}