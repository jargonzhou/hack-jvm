/**
 * Demonstration of 'tailrec'.
 */
package com.spike.language.kotlin.core.fp

fun fact(k: Int): Int {
    // tailrec
    tailrec fun factTail(m: Int, acc: Int): Int {
        if (m == 0) return acc
        else return factTail(m - 1, acc * m)
    }

    return factTail(k, 1)
}

fun main(args: Array<String>) {
    println(fact(5)) // 120
}