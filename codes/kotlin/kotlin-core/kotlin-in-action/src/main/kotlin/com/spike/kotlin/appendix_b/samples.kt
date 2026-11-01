package com.spike.kotlin.appendix_b

/**
 * A very basic usage of [Greeter].
 *
 * The output of this function will appear in the generated docs.
 */
fun basicGreeting() {
    // Create a greeter with the default name "World"
    val greeter = Greeter("World")
    println(greeter.greet())
}

/**
 * Shows how you can provide a custom name.
 */
fun greetingWithCustomName() {
    val greeter = Greeter("Alice")
    println(greeter.greet())
}