/**
 * Demonstration of generic functions.
 */
package com.spike.language.kotlin.core.fp


fun <T> printRepeat(t: T, times: Int): Unit {
    for (i in 1..times) {
        println(t)
    }
}

fun main(args: Array<String>) {
    //1
    //1
    printRepeat(1, 2)
}
