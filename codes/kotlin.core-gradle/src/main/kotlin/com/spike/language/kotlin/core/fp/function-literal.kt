/**
 * Demonstration of function literal.
 */
package com.spike.language.kotlin.core.fp

fun main(args: Array<String>) {
    val printHello = { println("Hello") }
    printHello()

    var printMessage = { message: String -> println(message) }
    printMessage("Hello")

    printMessage = { println(it) } // special variable: 'it'
    printMessage("Hello")
}