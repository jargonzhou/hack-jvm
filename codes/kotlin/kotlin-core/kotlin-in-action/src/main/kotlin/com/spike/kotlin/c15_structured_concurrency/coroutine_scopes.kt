package com.spike.kotlin.c15_structured_concurrency

import com.spike.kotlin.c14_coroutines.log
import kotlinx.coroutines.*
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

// =============================================================================
// CoroutineScope
// =============================================================================

// VM Options: -Dkotlinx.coroutines.debug
//fun main() {
//    // block: suspend CoroutineScope.() -> T
//    runBlocking {
//        // block: suspend CoroutineScope.() -> Unit
//        launch {
//            delay(1.seconds)
//            launch {
//                delay(250.milliseconds)
//                log("Grandchild done")
//            }
//            log("Child 1 done!")
//        }
//        launch {
//            delay(500.milliseconds)
//            log("Child 2 done!")
//        }
//        log("Parent done!")
//    }
//    //   0 [main @coroutine#1] Parent done!
//    // 529 [main @coroutine#3] Child 2 done!
//    //1024 [main @coroutine#2] Child 1 done!
//    //1288 [main @coroutine#4] Grandchild done
//}

// =============================================================================
// coroutineScope()
// concurrent decomposition of work
// =============================================================================

suspend fun generateValue(): Int {
    delay(500.milliseconds)
    return Random.nextInt(0, 10)
}

suspend fun computeSum() {
    log("Computing a sum...")
    // block: suspend CoroutineScope.() -> RT
    val sum = coroutineScope {
        val a = async { generateValue() }
        val b = async { generateValue() }
        a.await() + b.await()
    }
    log("Sum is $sum")
}

//fun main() {
//    runBlocking {
//        computeSum()
//    }
//    //  0 [main @coroutine#1] Computing a sum...
//    //624 [main @coroutine#1] Sum is 7
//}

// =============================================================================
// CoroutinScope()
// =============================================================================

class ComponentWithScope(dispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val scope = CoroutineScope(dispatcher + SupervisorJob())

    fun start() {
        log("Starting")
        // fun CoroutineScope.launch(...)
        scope.launch {
            while (true) {
                delay(500.milliseconds)
                log("Component working")
            }
        }
        scope.launch {
            log("Doing a one-off task...")
            delay(500.milliseconds)
            log("Task done!")
        }
    }

    fun stop() {
        log("Stopping")
        scope.cancel()
    }
}

//fun main() {
//    val c = ComponentWithScope()
//    c.start()
//    Thread.sleep(2000)
//    c.stop()
//
//    //   0 [main] Starting
//    // 149 [DefaultDispatcher-worker-2 @coroutine#2] Doing a one-off task...
//    // 689 [DefaultDispatcher-worker-1 @coroutine#1] Component working
//    // 689 [DefaultDispatcher-worker-2 @coroutine#2] Task done!
//    //1202 [DefaultDispatcher-worker-2 @coroutine#1] Component working
//    //1717 [DefaultDispatcher-worker-2 @coroutine#1] Component working
//    //2115 [main] Stopping
//}

// =============================================================================
// coroutine context and structured concurrency
// =============================================================================
//fun main() {
//    runBlocking(Dispatchers.Default) {
//        log(coroutineContext) // #1
//        launch {
//            log(coroutineContext) // #2
//            launch(Dispatchers.IO + CoroutineName("mine")) {
//                log(coroutineContext) // #3
//            }
//        }
//    }
//    //  0 [DefaultDispatcher-worker-1 @coroutine#1] [CoroutineId(1), "coroutine#1":BlockingCoroutine{Active}@17ed752b, Dispatchers.Default]
//    //129 [DefaultDispatcher-worker-2 @coroutine#2] [CoroutineId(2), "coroutine#2":StandaloneCoroutine{Active}@5654c355, Dispatchers.Default]
//    //183 [DefaultDispatcher-worker-1 @mine#3] [CoroutineName(mine), CoroutineId(3), "mine#3":StandaloneCoroutine{Active}@de61bc3, Dispatchers.IO]
//}

// Job
//@OptIn(ExperimentalCoroutinesApi::class)
//fun main() {
//    runBlocking(CoroutineName("A")) {
//        log("A's job: ${coroutineContext.job}")
//        launch(CoroutineName("B")) {
//            log("B's job: ${coroutineContext.job}")
//            log("B's parent: ${coroutineContext.job.parent}")
//        }
//        log("A's children: ${coroutineContext.job.children.toList()}")
//    }
//    //  0 [main @A#1] A's job: "A#1":BlockingCoroutine{Active}@4f47d241
//    //132 [main @A#1] A's children: ["B#2":StandaloneCoroutine{Active}@5f150435]
//    //144 [main @B#2] B's job: "B#2":StandaloneCoroutine{Active}@5f150435
//    //144 [main @B#2] B's parent: "A#1":BlockingCoroutine{Completing}@4f47d241
//}

// Job with coroutineScope
@OptIn(ExperimentalCoroutinesApi::class)
fun main() {
    runBlocking(CoroutineName("A")) {
        log("A's job: ${coroutineContext.job}")
        coroutineScope {
            log("B's job: ${coroutineContext.job}")
            log("B's parent: ${coroutineContext.job.parent}")
            launch(CoroutineName("B")) {
                log("C's parent: ${coroutineContext.job.parent}")
            }
        }
    }
    // 0 [main @A#1] A's job: "A#1":BlockingCoroutine{Active}@4f47d241
    //66 [main @A#1] B's job: "A#1":ScopeCoroutine{Active}@768debd
    //66 [main @A#1] B's parent: "A#1":BlockingCoroutine{Active}@4f47d241
    //79 [main @B#2] C's parent: "A#1":ScopeCoroutine{Completing}@768debd
}