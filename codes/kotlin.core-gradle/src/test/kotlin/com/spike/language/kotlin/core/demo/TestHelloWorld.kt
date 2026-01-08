package com.spike.language.kotlin.core.demo

import kotlin.test.assertEquals
import org.junit.Test

class TestSource {
    @Test
    fun f() {
        val joiner = KotlinGreetingJoiner(Greeter("Hello"))
        joiner.addName("Alice")
        assertEquals("Hello Alice", joiner.getJoinedGreeting())
    }
}