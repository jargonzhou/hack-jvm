package com.spike.kotlin.c18_error_handling_testing

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.time.Duration.Companion.milliseconds

class PlaygroundTest {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun testDelay() {
//        //    context: CoroutineContext = EmptyCoroutineContext,
//        //    timeout: Duration = DEFAULT_TIMEOUT.getOrThrow(),
//        //    testBody: suspend TestScope.() -> Unit
//        runTest {
//            val startTime = System.currentTimeMillis()
//            delay(20.seconds)
//            println(System.currentTimeMillis() - startTime)
//        }
//        // 4

//        runTest {
//            var x = 0
//            launch {
//                delay(500.milliseconds)
//                x++
//            }
//            launch {
//                delay(1.seconds)
//                x++
//            }
//            println(currentTime) // 0
//
//            delay(600.milliseconds)
//            assertEquals(1, x)
//            println(currentTime) // 600
//
//            delay(500.milliseconds)
//            assertEquals(2, x)
//            println(currentTime) // 1100
//        }

        runTest {
            var x = 0
            launch {
                x++
                launch {
                    x++
                }
            }
            launch {
                delay(200.milliseconds)
                x++
            }

            runCurrent()
            assertEquals(2, x)

            advanceUntilIdle()
            assertEquals(3, x)
        }
    }
}