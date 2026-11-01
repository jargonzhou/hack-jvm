package com.spike.kotlin.appendix_b

/**
 * A simple class that greets a user.
 *
 * @property name the name to greet
 *
 * @sample com.spike.kotlin.appendix_b.basicGreeting
 * @sample com.spike.kotlin.appendix_b.greetingWithCustomName
 */
class Greeter(val name: String) {

    /**
     * Returns a greeting string.
     */
    fun greet(): String = "Hello, $name!"
}