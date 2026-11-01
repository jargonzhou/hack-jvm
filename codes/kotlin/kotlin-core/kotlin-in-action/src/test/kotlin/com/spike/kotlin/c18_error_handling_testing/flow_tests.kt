package com.spike.kotlin.c18_error_handling_testing

import app.cash.turbine.test
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

val myFlow = flow {
    emit(1)
    emit(2)
    emit(3)
}

class FlowTests {
    @Test
    fun doTest() {
        runTest {
            val results = myFlow.test {
                assertEquals(1, awaitItem())
                assertEquals(2, awaitItem())
                assertEquals(3, awaitItem())

                awaitComplete()
            }
        }
    }
}