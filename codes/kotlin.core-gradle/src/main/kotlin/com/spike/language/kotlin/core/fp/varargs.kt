/**
 * Demonstration of 'vararg'.
 */
package com.spike.language.kotlin.core.fp

// vararg can also not the last parameter
fun multiprint(prefix: String, vararg strings: String, suffix: String): Unit {
    println(prefix)
    for (string in strings)
        println(string)
    println(suffix)
}

fun main(args: Array<String>) {
    multiprint("String", "a", "b", "c", suffix = ".")
    // spread operator: *
    multiprint("String", *arrayOf("a", "b", "c"), suffix = ".")
}