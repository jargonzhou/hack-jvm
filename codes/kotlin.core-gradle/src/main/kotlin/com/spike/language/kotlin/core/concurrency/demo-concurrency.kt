package com.spike.language.kotlin.core.concurrency

import kotlin.concurrent.thread

/**
 * Demonstration of concurrency methods in JVM
 */

fun main(args: Array<String>) {
    // syntax sugar of creating java.lang.Thread
    var times = 10
    val t = thread(start = false, name = "demo-concurrency", block = {
        while (times > 0) {
            print(".")
            Thread.sleep(100)
            times--
        }
    })
    t.start()
}
