package com.spike.kotlin.c13_dsl

class Greeter(val greeting: String) {
    operator fun invoke(name: String) {
        println("$greeting, $name!")
    }

    // overloads
    operator fun invoke(name: String, punctuation: String) {
        println("$greeting, $name$punctuation")
    }
}

fun main() {
    val greeter = Greeter("Servus")
    greeter("Dmitry")
    // Servus, Dmitry!

    greeter("Dmitry", "!!!")
    // Servus, Dmitry!!!
}

///** A function that takes 2 arguments. */
//public interface Function2<in P1, in P2, out R> : Function<R> {
//    /** Invokes the function with the specified arguments. */
//    public operator fun invoke(p1: P1, p2: P2): R
//}