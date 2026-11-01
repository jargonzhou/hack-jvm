package com.spike.kotlin.c16_flows

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.milliseconds

// =============================================================================
// flow()
//   block: suspend FlowCollector<T>.() -> Unit
// emit()
// collect()
//
// Flow<out T>
// FlowCollector<in T>
// =============================================================================


// an infinite flow
val counterFlow = flow {
    var x = 0
    while (true) {
        emit(x++)
        delay(200.milliseconds)
    }
}